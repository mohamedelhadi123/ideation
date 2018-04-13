package org.exoplatform.addons.ideation.portlets.ideations;

import juzu.*;
import juzu.plugin.jackson.Jackson;
import juzu.request.RequestContext;
import juzu.template.Template;
import org.exoplatform.addons.ideation.portlets.commons.BaseController;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.entities.dto.IdeaDTO;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.commons.juzu.ajax.Ajax;
import juzu.request.SecurityContext;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.inject.Inject;
import javax.portlet.PortletPreferences;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class IdeationController extends BaseController {
    protected static Log log = ExoLogger.getLogger(IdeationController.class);

    @Inject
    @Path("index.gtmpl")
    Template indexTemplate;


    @Inject
    @Path("create_idea.gtmpl")
    Template create_ideaTemplate;

    @Inject
    PortletPreferences portletPreferences;





    @Inject
    IdeaService ideaService;


    @View
    public Response.Content index(RequestContext requestContext,SecurityContext securityContext) {
        Map<String, Object> parameters = new HashMap<>();
        String currentUser = securityContext.getRemoteUser();

        parameters.put("currentUser",currentUser);

        return indexTemplate.with(parameters).ok();
    }




    @Resource(method = HttpMethod.POST)
    @Ajax
    public Response init(SecurityContext securityContext) throws JSONException {
        String currentUser = securityContext.getRemoteUser();
        if (currentUser == null) {
            return Response.status(401).body("You must login to create new Idea");
        }
        JSONObject user = new JSONObject();
        user.put("username",currentUser);
        JSONArray ideasJson = new JSONArray();
        return Response.ok(ideasJson.toString()).withMimeType("application/json; charset=UTF-8").withHeader("Cache-Control", "no-cache");
    }

    @Resource(method = HttpMethod.POST)
    @Ajax
    public Response delete(String title, SecurityContext securityContext) throws JSONException {

        //--- Get context pref each time we refresh the page
        //TODO should be used once
        //--- Get Idea by title
        IdeaEntity cEntity= ideaService.findIdeaByTitle(title);
        //--- Delete Idea
        ideaService.deleteIdea(cEntity);

        return Response.ok("{\"status\":\"ok\"}").withMimeType("application/json; charset=UTF-8").withHeader("Cache-Control", "no-cache");
    }

    @Resource(method = HttpMethod.POST)
    @Ajax
    public Response add(String title,String description,String owner, SecurityContext securityContext) throws JSONException {

        //--- Get context pref each time we refresh the page
        //TODO should be used once
        //--- build an Idea
        IdeaEntity cEntity = new IdeaEntity();
        IdeaDTO entity = new IdeaDTO(cEntity);
        cEntity.setTitle(title);
        cEntity.setDescription(description);
        String currentUser = securityContext.getRemoteUser();
        if (currentUser != null) {
            cEntity.setCreatedBy(currentUser);
        }
        IdeaEntity cEntityPersisted = ideaService.createIdea(cEntity);
        JSONObject json = new JSONObject(cEntityPersisted);
        return Response.ok(json.toString()).withMimeType("application/json; charset=UTF-8").withHeader("Cache-Control", "no-cache");
    }

    @Resource(method = HttpMethod.POST)
    @Ajax

    public Response update(String id, String title,String description, SecurityContext securityContext) throws JSONException {

        //--- Get context pref each time we refresh the page
        //TODO should be used once
        //--- build a Idea
        IdeaEntity cEntity = ideaService.findIdeaById(Long.parseLong(id));
        cEntity.setTitle(title);
        cEntity.setDescription(description);
        //--- Delete Idea
        IdeaEntity cEntityPersisted = ideaService.updateIdea(cEntity);
        JSONObject json = new JSONObject(cEntityPersisted);

        return Response.ok(json.toString()).withMimeType("application/json; charset=UTF-8").withHeader("Cache-Control", "no-cache");
    }

    @MimeType.JSON
    @Jackson
    @Ajax
    public List<IdeaDTO> getAllIdeas(){
        try {
        List<IdeaDTO> ideas = ideaService.getAllIdeas() ;
        return ideas;
    } catch (Throwable e) {
        log.error(e);
        return null;
    }
    }



    @Ajax
    @juzu.Resource
    @MimeType.JSON
    @Jackson
    public Response getBundle(String locale) {
        return super.getBundle(new Locale(locale));
    }

    @Override
    public Log getLogger() {
        return log;
    }

}