package org.exoplatform.addons.ideation.portlets.ideaFront;

import juzu.*;
import juzu.impl.common.JSON;
import juzu.plugin.jackson.Jackson;
import juzu.template.Template;
import org.apache.commons.fileupload.FileItem;
import org.exoplatform.commons.juzu.ajax.Ajax;
import org.exoplatform.commons.utils.PropertyManager;
import org.exoplatform.ideation.entities.dto.CommentDTO;
import org.exoplatform.ideation.entities.dto.FavoriteDTO;
import org.exoplatform.ideation.entities.dto.IdeaDTO;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.ideation.service.impl.CommentService;
import org.exoplatform.ideation.service.impl.FavoriteService;
import org.exoplatform.ideation.storage.Utils;
import org.exoplatform.portal.application.PortalRequestContext;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
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
import java.util.*;


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
    IdentityManager identityManager;

    @Inject
    RepositoryService repositoryService;

    @Inject
    CommentService commentService;


    @Inject
    IdeaService ideaService;


    @Inject
    FavoriteService favoriteService;

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
    public void saveFavorite(@Jackson FavoriteDTO obj) {
        if (currentUser != null) {
            obj.setAuthor(currentUser);
        }
            obj = favoriteService.save(obj);

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
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public Response getContext() {
        try {
            JSON data = new JSON();
            data.set("currentUser",currentUser);
            Profile profile=identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, currentUser, false).getProfile();
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
    public List<IdeaDTO> getIdeas() {
        try {
            return ideaService.getPublishedIdeas(currentUser);
        } catch (Throwable e) {
            return null;
        }
    }

    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public List<CommentDTO> getComments(@Jackson IdeaDTO obj) {

          return  commentService.getCommentsByIdeaId();


    }

    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public List<IdeaDTO> getDraftedIdeasOfCurrentUser(String status) {
        try {
            if (status != null) {
                if (status.equals("DRAFTED")) {
                    return ideaService.getDraftIdeas(currentUser);
                } else {
                    return ideaService.getPublishedIdeas("PUBLISHED");
                }
                }else{
            return null;
        }

        } catch (Throwable e) {
            log.error(e);
            return null;
        }

    }

    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public List<FavoriteDTO> getFavoritesByUserId() {

        return favoriteService.getFavoritesByUserId(currentUser);


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
    public Response getAttachements(@Jackson IdeaDTO obj) {
        SessionProvider sessionProvider = SessionProvider.createSystemProvider();
        List<JSON> atts = new ArrayList<JSON>();
        try {
            Session session = sessionProvider.getSession("collaboration",
                    repositoryService.getCurrentRepository());
            Node rootNode = session.getRootNode();
            Long requestId=obj.getId();
            if (rootNode.hasNode("Application Data/ideation/Ideas/req_"+requestId)) {
                Node requestsFolders= rootNode.getNode("Application Data/ideation/Ideas/req_"+requestId);
                NodeIterator iter = requestsFolders.getNodes();
                while (iter.hasNext()) {
                    Node node = (Node) iter.next();
                    JSON attachment=new JSON();
                    attachment.set("name",node.getName());
                    attachment.set("url","/rest/jcr/repository/collaboration/Application Data/ideation/Ideas/req_"+requestId+"/"+node.getName());
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
        if (currentUser != null) {
            obj.setAuthor(currentUser);
        }
        commentService.save(obj);
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
