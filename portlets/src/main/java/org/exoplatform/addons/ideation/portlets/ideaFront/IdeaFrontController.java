package org.exoplatform.addons.ideation.portlets.ideaFront;

import juzu.*;
import juzu.impl.common.JSON;
import juzu.plugin.jackson.Jackson;
import juzu.template.Template;
import org.apache.commons.fileupload.FileItem;
import org.exoplatform.commons.juzu.ajax.Ajax;
import org.exoplatform.commons.utils.PropertyManager;
import org.exoplatform.ideation.entities.dto.*;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.ideation.service.impl.CommentService;
import org.exoplatform.ideation.service.impl.FavoriteService;
import org.exoplatform.ideation.service.impl.LikeService;
import org.exoplatform.ideation.service.impl.RateService;
import org.exoplatform.ideation.storage.Utils;
import org.exoplatform.portal.application.PortalRequestContext;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.listener.ListenerService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.identity.model.Profile;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;
import org.exoplatform.social.core.manager.IdentityManager;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import org.exoplatform.social.core.identity.model.Identity;

public class IdeaFrontController {
    private static Log log = ExoLogger.getLogger(IdeaFrontController.class);

    ResourceBundle bundle;

    @Inject
    @Path("create_idea.gtmpl")
    Template create_ideaTmpl;

    @Inject
    @Path("index.gtmpl")
    Template indexTmpl;

    @Inject
    ListenerService listenerService;

    @Inject
    IdentityManager identityManager;

    @Inject
    RepositoryService repositoryService;

    @Inject
    CommentService commentService;


    @Inject
    RateService rateService;

    @Inject
    IdeaService ideaService;


    @Inject
    FavoriteService favoriteService;

    @Inject
    LikeService likeService ;

    private String bundleString;

    private String currentUser = ConversationState.getCurrent().getIdentity().getUserId();



    @View
    public Response.Content index() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("currentUser",currentUser);
        return indexTmpl.with(parameters).ok();
    }



    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public void saveIdea(@Jackson IdeaDTO obj) {

        ConversationState conversationState = ConversationState.getCurrent();

        if (conversationState != null) {

            obj.setCreatedBy(conversationState.getIdentity().getUserId());

            obj = ideaService.save(obj,true);

        } else {

            //TODO add log message
            log.warn("");

        }

        //todo return your object
    }



    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public void saveLIke(@Jackson LikeDTO obj) {
        ConversationState conversationState = ConversationState.getCurrent();

        if (conversationState != null) {
            obj.setAuthor(conversationState.getIdentity().getUserId());
        }
        long id = obj.getIdeaId();
        likeService.save(obj,currentUser,id);
        if (obj.isLike()) {
            try {
                listenerService.broadcast("exo.ideation.ideaLike", "", obj);
            } catch (Exception e) {
                log.error("Cannot broadcast like event");

            }
        }
    }


    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public void SaveDraft(@Jackson IdeaDTO obj) {
        if (currentUser != null) {
            obj.setCreatedBy(currentUser);
        }
        obj = ideaService.save(obj,true);
    }


    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public void SaveFavorite(@Jackson FavoriteDTO obj) {
        if (currentUser != null) {
            obj.setAuthor(currentUser);
        }

        long id = obj.getIdeaId();

        favoriteService.save(obj,currentUser,id);
    }


    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public Response getContext() {
        ConversationState conversationState = ConversationState.getCurrent();

        try {
            JSON data = new JSON();
            data.set("currentUser",conversationState.getIdentity().getUserId());
            Profile profile=identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, conversationState.getIdentity().getUserId(), false).getProfile();
            if(profile.getAvatarUrl()!=null){
                data.set("currentUserAvatar",profile.getAvatarUrl());
            }else{
                data.set("currentUserAvatar","/eXoSkin/skin/images/system/UserAvtDefault.png");
            }
            data.set("currentUserName",profile.getFullName());
            return Response.ok(data.toString());
        } catch (Throwable e) {
            log.error("error while getting context", e);
            return Response.status(500);
        }
    }

    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public List<LikeDTO> getLikes(@Jackson LikeDTO obj) {
        return likeService.getLikes();
    }

    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public String getResults(@Jackson IdeaDTO obj){
        List<IdeaDTO> ideas =ideaService.getUserIdeas(currentUser);
        List<RateDTO> rates = rateService.getRates();
        for(IdeaDTO idea : ideas) {
            for (RateDTO rate: rates) {
                if (rate.getIdeaId() == idea.getId()) {
                    long countRate = rateService.countRates(idea.getId());
                    long rat = 0;
                    List<RateDTO> ratin = rateService.count(idea.getId());
                    for (RateDTO ratecount : ratin) {
                        rat += ratecount.getRate();
                        idea.setNumRate(rat);
                        idea.setCountRate(countRate);
                        float result = (float) rat / (float) countRate;
                        idea.setResult(new DecimalFormat("##.##").format(result));
                    }
                }
            }
        }
        return obj.getResult();
    }

    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public List<RateDTO> getRates(@Jackson RateDTO obj) {
        return rateService.getRates();
    }




    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public List<IdeaDTO> getIdeas() {
        String currentUser = ConversationState.getCurrent().getIdentity().getUserId();
        List<FavoriteDTO> favs = favoriteService.getFavorites(currentUser);
        List<RateDTO> rates = rateService.getRates();

        LikeDTO likeDTO = new LikeDTO();
        List<LikeDTO> likes = likeService.getLikes();
        List<CommentDTO> comments = commentService.getCommentsByIdeaId();
            try {
                List<IdeaDTO> ideas = ideaService.getUserIdeas(currentUser);
                for (IdeaDTO idea : ideas) {
                    idea.setRated(false);
                    idea.setFav(false);
                    idea.setLike(false);
                    Profile profile = identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, idea.getCreatedBy(), false).getProfile();
                    if (profile.getAvatarUrl() != null) {
                        idea.setPosterAvatar(profile.getAvatarUrl());
                    } else {
                        idea.setPosterAvatar("/eXoSkin/skin/images/system/UserAvtDefault.png");
                    }
                    for (FavoriteDTO fav : favs) {
                        if (fav.getAuthor().equals(currentUser) && fav.getIdeaId() == idea.getId()) {
                            idea.setFav(true);
                            long countfav = favoriteService.count(idea.getId());
                            idea.setNumfav(countfav);
                            break;
                        }
                        if (fav.getIdeaId() == idea.getId()) {
                            long count = favoriteService.count(idea.getId());
                            idea.setNumfav(count);

                        }

                    }
                    for (LikeDTO like : likes) {
                        if (like.getAuthor().equals(currentUser) && like.getIdeaId() == idea.getId()) {
                            idea.setLike(true);
                            long count = likeService.count(idea.getId());
                            idea.setNumlike(count);
                            break;
                        }
                        if (like.getIdeaId() == idea.getId()) {
                            long count = likeService.count(idea.getId());
                            idea.setNumlike(count);

                        }
                    }

                    for (CommentDTO comment : comments) {
                        if (comment.getAuthor().equals(currentUser) && comment.getIdeaId() == idea.getId()) {
                            idea.setCommentText(comment.getCommentText());
                            long countcomment = commentService.countcomment(idea.getId());
                            idea.setNumcomments(countcomment);
                            break;
                        }
                        if (comment.getIdeaId() == idea.getId()) {
                            long countcomment = commentService.countcomment(idea.getId());
                            idea.setNumcomments(countcomment);

                        }
                    }
                    List<RateDTO> ratin = rateService.count(idea.getId());
                    for (RateDTO rate : rates) {
                        if (rate.getAuthor().equals(currentUser) && rate.getIdeaId() == idea.getId()) {
                            idea.setRated(true);
                            idea.setRate(rate);
                        }
                        long countRate = rateService.countRates(idea.getId());
                        long rat = 0;
                        for (RateDTO ratecount : ratin) {
                            rat += ratecount.getRate();
                            idea.setNumRate(rat);
                            idea.setCountRate(countRate);
                            float result = (float) rat / (float) countRate;
                            idea.setResult(new DecimalFormat("##.##").format(result));
                        }


                    }
                    rateService.count(idea.getId());

                }
                return ideas;

            } catch (Throwable e) {
                return null;
            }

    }


    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public IdeaDTO getIdea(Long id) {
       String currentUser = ConversationState.getCurrent().getIdentity().getUserId();
        List<FavoriteDTO> favs = favoriteService.getFavorites(currentUser);
        List<RateDTO> rates = rateService.getRates();
        List<LikeDTO> likes = likeService.getLikes();
        List<CommentDTO> comments = commentService.getCommentsByIdeaId();

        try {
            IdeaDTO idea =ideaService.getIdea(id);
            idea.setRated(false);
            idea.setFav(false);
            idea.setLike(false);
            Profile profile=identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, idea.getCreatedBy(), false).getProfile();
            if(profile.getAvatarUrl()!=null){
                idea.setPosterAvatar(profile.getAvatarUrl());
            }else{
                idea.setPosterAvatar("/eXoSkin/skin/images/system/UserAvtDefault.png");
            }
            for (FavoriteDTO fav: favs){
                if (fav.getAuthor().equals(currentUser) && fav.getIdeaId()==idea.getId()){
                    idea.setFav(true);
                    long countfav = favoriteService.count(idea.getId());
                    idea.setNumfav(countfav);
                    break;
                }
                if(fav.getIdeaId()==idea.getId()){
                    long count = favoriteService.count(idea.getId());
                    idea.setNumfav(count);

                }
            }
            for (LikeDTO like: likes){
                if (like.getAuthor().equals(currentUser)&& like.getIdeaId()==idea.getId()){
                    idea.setLike(true);
                    long count = likeService.count(idea.getId());
                    idea.setNumlike(count);
                    break;
                }
                if(like.getIdeaId()==idea.getId()){
                    long count = likeService.count(idea.getId());
                    idea.setNumlike(count);

                }
            }

            for (CommentDTO comment: comments){
                if (comment.getAuthor().equals(currentUser)&& comment.getIdeaId()==idea.getId()){
                    idea.setCommentText(comment.getCommentText());
                    long countcomment = commentService.countcomment(idea.getId());
                    idea.setNumcomments(countcomment);

                    break;
                }
                if(comment.getIdeaId()==idea.getId()){
                    long countcomment = commentService.countcomment(idea.getId());
                    idea.setNumcomments(countcomment);

                }
            }
            for (RateDTO rate: rates) {
                if (rate.getAuthor().equals(currentUser) && rate.getIdeaId() == idea.getId()) {
                    idea.setRated(true);
                    idea.setRate(rate);
                    break;
                }
                long countRate = rateService.countRates(idea.getId());
                long rat = 0;
                List<RateDTO> ratin=rateService.count(idea.getId());
                for (RateDTO ratecount : ratin ) {
                    rat+=ratecount.getRate();
                    idea.setNumRate(rat);
                    idea.setCountRate(countRate);
                    float result = (float)rat/(float)countRate;
                    idea.setResult(new DecimalFormat("##.##").format(result));
                }

            }
            rateService.count(idea.getId());


            return idea;
        } catch (Throwable e) {
            return null;
        }

    }



    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public List<CommentDTO> getComments() {
        try {
            List<CommentDTO> comments= commentService.getCommentsByIdeaId();
            for (CommentDTO comment : comments){
                Profile profile=identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, comment.getAuthor(), false).getProfile();
                if(profile.getAvatarUrl()!=null){
                    comment.setPosterAvatar(profile.getAvatarUrl());
                }else{
                    comment.setPosterAvatar("/eXoSkin/skin/images/system/UserAvtDefault.png");
                }
            }
            return comments;
        } catch (Throwable e) {
            log.error(e);
            return null;
        }
    }

    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public List<FavoriteDTO> getFavorite(@Jackson FavoriteDTO favoriteDTO) {
        String currentUser = ConversationState.getCurrent().getIdentity().getUserId();
        List<FavoriteDTO> favs = favoriteService.getFavorites(currentUser);
        List<RateDTO> rates = rateService.getRates();

        LikeDTO likeDTO = new LikeDTO();
        List<LikeDTO> likes = likeService.getLikes();
        List<CommentDTO> comments = commentService.getCommentsByIdeaId();
        try {
            List<FavoriteDTO> ideas = favoriteService.getFavorites(currentUser);
            for (FavoriteDTO idea : ideas) {
                idea.getIdea().setRated(false);
                idea.getIdea().setLike(false);
                Profile profile = identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, idea.getIdea().getCreatedBy(), false).getProfile();
                if (profile.getAvatarUrl() != null) {
                    idea.getIdea().setPosterAvatar(profile.getAvatarUrl());
                } else {
                    idea.getIdea().setPosterAvatar("/eXoSkin/skin/images/system/UserAvtDefault.png");
                }

                for (LikeDTO like : likes) {
                    if (like.getAuthor().equals(currentUser) && like.getIdeaId() == idea.getIdea().getId()) {
                        idea.getIdea().setLike(true);
                        long count = likeService.count(idea.getId());
                        idea.getIdea().setNumlike(count);
                        break;
                    }
                    if (like.getIdeaId() == idea.getIdea().getId()) {
                        long count = likeService.count(idea.getIdea().getId());
                        idea.getIdea().setNumlike(count);

                    }
                }

                for (CommentDTO comment : comments) {
                    if (comment.getAuthor().equals(currentUser) && comment.getIdeaId() == idea.getIdea().getId()) {
                        idea.getIdea().setCommentText(comment.getCommentText());
                        long countcomment = commentService.countcomment(idea.getIdea().getId());
                        idea.getIdea().setNumcomments(countcomment);
                        break;
                    }
                    if (comment.getIdeaId() == idea.getIdea().getId()) {
                        long countcomment = commentService.countcomment(idea.getIdea().getId());
                        idea.getIdea().setNumcomments(countcomment);

                    }
                }
                List<RateDTO> ratin = rateService.count(idea.getIdea().getId());
                for (RateDTO rate : rates) {
                    if (rate.getAuthor().equals(currentUser) && rate.getIdeaId() == idea.getIdea().getId()) {
                        idea.getIdea().setRated(true);
                        idea.getIdea().setRate(rate);
                    }
                    long countRate = rateService.countRates(idea.getIdea().getId());
                    long rat = 0;
                    for (RateDTO ratecount : ratin) {
                        rat += ratecount.getRate();
                        idea.getIdea().setNumRate(rat);
                        idea.getIdea().setCountRate(countRate);
                        float result = (float) rat / (float) countRate;
                        idea.getIdea().setResult(new DecimalFormat("##.##").format(result));
                    }


                }
                rateService.count(idea.getIdea().getId());

            }
            return ideas;

        } catch (Throwable e) {
            return null;
        }


    }

    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public List<IdeaDTO> getDraftIdeas(@Jackson IdeaDTO obj) {
        String currentUser = ConversationState.getCurrent().getIdentity().getUserId();
        List<FavoriteDTO> favs = favoriteService.getFavorites(currentUser);
        List<RateDTO> rates = rateService.getRates();

        LikeDTO likeDTO = new LikeDTO();
        List<LikeDTO> likes = likeService.getLikes();
        List<CommentDTO> comments = commentService.getCommentsByIdeaId();
        try {
            List<IdeaDTO> ideas = ideaService.getDraftIdeas(currentUser);
            for(IdeaDTO idea : ideas){
                idea.setRated(false);
                idea.setFav(false);
                idea.setLike(false);
                Profile profile=identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, idea.getCreatedBy(), false).getProfile();
                if(profile.getAvatarUrl()!=null){
                    idea.setPosterAvatar(profile.getAvatarUrl());
                }else{
                    idea.setPosterAvatar("/eXoSkin/skin/images/system/UserAvtDefault.png");
                }
                for (FavoriteDTO fav: favs){
                    if (fav.getAuthor().equals(currentUser)&& fav.getIdeaId()==idea.getId()){
                        idea.setFav(true);
                        long countfav = favoriteService.count(idea.getId());
                        idea.setNumfav(countfav);
                        break;
                    }
                    if(fav.getIdeaId()==idea.getId()){
                        long count = favoriteService.count(idea.getId());
                        idea.setNumfav(count);

                    }
                }
                for (LikeDTO like: likes){
                    if (like.getAuthor().equals(currentUser)&& like.getIdeaId()==idea.getId()){
                        idea.setLike(true);
                        long count = likeService.count(idea.getId());
                        idea.setNumlike(count);
                        break;
                    }
                    if(like.getIdeaId()==idea.getId()){
                        long count = likeService.count(idea.getId());
                        idea.setNumlike(count);

                    }
                }

                for (CommentDTO comment: comments){
                    if (comment.getAuthor().equals(currentUser)&& comment.getIdeaId()==idea.getId()){
                        idea.setCommentText(comment.getCommentText());
                        long countcomment = commentService.countcomment(idea.getId());
                        idea.setNumcomments(countcomment);
                        break;
                    }
                    if(comment.getIdeaId()==idea.getId()){
                        long countcomment = commentService.countcomment(idea.getId());
                        idea.setNumcomments(countcomment);

                    }
                }
                List<RateDTO> ratin=rateService.count(idea.getId());
                for (RateDTO rate: rates) {
                    if (rate.getAuthor().equals(currentUser) && rate.getIdeaId() == idea.getId()) {
                        idea.setRated(true);
                        idea.setRate(rate);
                    }
                    long countRate = rateService.countRates(idea.getId());
                    long rat = 0;
                    for (RateDTO ratecount : ratin ) {
                        rat+=ratecount.getRate();
                        idea.setNumRate(rat);
                        idea.setCountRate(countRate);
                        float result = (float)rat/(float)countRate;
                        idea.setResult(new DecimalFormat("##.##").format(result));
                    }
                }
                rateService.count(idea.getId());
            }
            return ideas;
        } catch (Throwable e) {
            return null;
        }
    }

    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public List<IdeaDTO> getDraftedIdeasOfCurrentUser() {
        String currentUser = ConversationState.getCurrent().getIdentity().getUserId();
        List<FavoriteDTO> favs = favoriteService.getFavorites(currentUser);
        List<RateDTO> rates = rateService.getRates();

        LikeDTO likeDTO = new LikeDTO();
        List<LikeDTO> likes = likeService.getLikes();
        List<CommentDTO> comments = commentService.getCommentsByIdeaId();
        try {
            List<IdeaDTO> ideas = ideaService.getUserIdeas("PUBLISHED");
            for(IdeaDTO idea : ideas){
                idea.setRated(false);
                idea.setFav(false);
                idea.setLike(false);
                Profile profile=identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, idea.getCreatedBy(), false).getProfile();
                if(profile.getAvatarUrl()!=null){
                    idea.setPosterAvatar(profile.getAvatarUrl());
                }else{
                    idea.setPosterAvatar("/eXoSkin/skin/images/system/UserAvtDefault.png");
                }
                for (FavoriteDTO fav: favs){
                    if (fav.getAuthor().equals(currentUser)&& fav.getIdeaId()==idea.getId()){
                        idea.setFav(true);
                        long countfav = favoriteService.count(idea.getId());
                        idea.setNumfav(countfav);
                        break;
                    }
                    if(fav.getIdeaId()==idea.getId()){
                        long count = favoriteService.count(idea.getId());
                        idea.setNumfav(count);

                    }
                }
                for (LikeDTO like: likes){
                    if (like.getAuthor().equals(currentUser)&& like.getIdeaId()==idea.getId()){
                        idea.setLike(true);
                        long count = likeService.count(idea.getId());
                        idea.setNumlike(count);
                        break;
                    }
                    if(like.getIdeaId()==idea.getId()){
                        long count = likeService.count(idea.getId());
                        idea.setNumlike(count);
                    }
                }

                for (CommentDTO comment: comments){
                    if (comment.getAuthor().equals(currentUser)&& comment.getIdeaId()==idea.getId()){
                        idea.setCommentText(comment.getCommentText());
                        long countcomment = commentService.countcomment(idea.getId());
                        idea.setNumcomments(countcomment);
                        break;
                    }
                    if(comment.getIdeaId()==idea.getId()){
                        long countcomment = commentService.countcomment(idea.getId());
                        idea.setNumcomments(countcomment);

                    }
                }
                List<RateDTO> ratin=rateService.count(idea.getId());
                for (RateDTO rate: rates) {
                    if (rate.getAuthor().equals(currentUser) && rate.getIdeaId() == idea.getId()) {
                        idea.setRated(true);
                        idea.setRate(rate);
                    }
                    long countRate = rateService.countRates(idea.getId());
                    long rat = 0;
                    for (RateDTO ratecount : ratin ) {
                        rat+=ratecount.getRate();
                        idea.setNumRate(rat);
                        idea.setCountRate(countRate);
                        float result = (float)rat/(float)countRate;
                        idea.setResult(new DecimalFormat("##.##").format(result));
                    }


                }
                rateService.count(idea.getId());


            }
            return ideas;
        } catch (Throwable e) {
            return null;
        }


    }

    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public void updateIdea(@Jackson IdeaDTO obj) {
        obj = ideaService.save(obj, false);

    }

    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public Response deleteIdea(@Jackson IdeaDTO obj) throws Exception {
        try {
            ideaService.delete(obj);
            return Response.ok();
        } catch (Exception e) {
            log.error("Error when updating Idea", e);
            return Response.error("");
        }
    }


    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public Response deleteFavorite(@Jackson FavoriteDTO obj) throws Exception {
        try {
            favoriteService.remove(obj);
            return Response.ok();
        } catch (Exception e) {
            log.error("Error when removing Idea", e);
            return Response.error("");
        }
    }

    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public Response deleteLike(@Jackson LikeDTO obj) throws Exception {
        try {
            likeService.remove(obj);
            return Response.ok();
        } catch (Exception e) {
            log.error("Error when removing Idea", e);
            return Response.error("");
        }
    }

    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public Set<String> getCoworkersIdea(@Jackson IdeaDTO obj) {
        long ideaId = obj.getId();
        return ideaService.getCoworker(ideaId);
    }





    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public Response getAttachements(@Jackson IdeaDTO obj) {
        SessionProvider sessionProvider = SessionProvider.createSystemProvider();
        List<JSON> atts = new ArrayList<JSON>();
        try {
            Session session = sessionProvider.getSession("collaboration",
                    repositoryService.getCurrentRepository());
            Node rootNode = session.getRootNode();
            long ideaId=ideaService.getMaxId()+1;
            if (rootNode.hasNode("Application Data/ideation/Ideas/req_"+ideaId)) {
                Node requestsFolders= rootNode.getNode("Application Data/ideation/Ideas/req_"+ideaId);
                NodeIterator iter = requestsFolders.getNodes();
                while (iter.hasNext()) {
                    Node node = (Node) iter.next();
                    JSON attachment=new JSON();
                    attachment.set("name",node.getName());
                    attachment.set("url","/rest/jcr/repository/collaboration/Application Data/ideation/Ideas/req_"+ideaId+"/"+node.getName());
                    atts.add(attachment);
                }
                return Response.ok(atts.toString());
            }else{
                return Response.ok();
            }

        } catch (Exception e) {

            log.error("Error while getting attachements: ", e);
            return null;
        } finally {
            sessionProvider.close();
        }
    }



    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public void saveComment(@Jackson CommentDTO obj) {
        ConversationState conversationState = ConversationState.getCurrent();

        if (currentUser != null) {
            obj.setAuthor(conversationState.getIdentity().getUserId());
        }
        commentService.save(obj);
            try {
                listenerService.broadcast("exo.ideation.ideaComment", currentUser, obj);
            } catch (Exception e) {
                log.error("Cannot broadcast comment event");
            }
        }




    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public void saveRate(@Jackson RateDTO obj) {
        ConversationState conversationState = ConversationState.getCurrent();

        if (currentUser != null) {
            obj.setAuthor(conversationState.getIdentity().getUserId());
        }
        long id = obj.getIdeaId();
        rateService.save(obj,true,currentUser,id);

            try {
                listenerService.broadcast("exo.ideation.ideaRate", "", obj);
            } catch (Exception e) {
                log.error("Cannot broadcast like event");

            }

    }


    @Ajax
    @Resource(method = HttpMethod.POST)
    @MimeType.JSON
    @Jackson
    public void updateRate(@Jackson RateDTO obj) {
        long id = obj.getIdeaId();

        rateService.save(obj,false,currentUser,id);
        try {
            listenerService.broadcast("exo.ideation.ideaRate", "", obj);
        } catch (Exception e) {
            log.error("Cannot broadcast like event");

        }
    }



    @Ajax
    @Resource
    @MimeType.JSON
    @Jackson
    public Response getBundle() {
        try {
            if (!PropertyManager.isDevelopping() && bundleString != null && getResourceBundle().getLocale().equals(PortalRequestContext.getCurrentInstance().getLocale())) {
                return Response.ok(bundleString);
            }
            bundle = getResourceBundle(PortalRequestContext.getCurrentInstance().getLocale());
            JSON data = new JSON();
            Enumeration<String> enumeration = getResourceBundle().getKeys();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                try {
                    data.set(key.replaceAll("(.*)\\.", ""), getResourceBundle().getObject(key));
                } catch (MissingResourceException e) {
                    // Nothing to do, this happens sometimes
                }
            }
            data.set("currentUser",currentUser);
            bundleString = data.toString();
            return Response.ok(bundleString);
        } catch (Throwable e) {
            log.error("error while getting ideas", e);
            return Response.status(500);
        }
    }

    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public Response uploadFile(Long requestId, FileItem file) throws IOException {

        if (file != null) {
            Utils.saveFile(file,"Ideas","req_"+requestId);
            return Response.ok();
        } else {
            return Response.notFound();
        }
    }


    @Ajax
    @Resource
    @MimeType.JSON
    @Jackson
    public Response getData() {
        try {
            JSON data = new JSON();
            return Response.ok(data.toString());
        } catch (Throwable e) {
            log.error("error while getting context", e);
            return Response.status(500);
        }
    }


    private ResourceBundle getResourceBundle(Locale locale) {
        return bundle = ResourceBundle.getBundle("locale.portlet.idea-addon", locale, this.getClass().getClassLoader());
    }

    private ResourceBundle getResourceBundle() {
        if (bundle == null) {
            bundle = getResourceBundle(PortalRequestContext.getCurrentInstance().getLocale());
        }
        return bundle;
    }
}