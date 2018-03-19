package org.exoplatform.addons.ideation.portlets;

import juzu.*;
import juzu.request.SecurityContext;
import juzu.template.Template;
import org.exoplatform.ideation.dao.IdeaDAO;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.commons.juzu.ajax.Ajax;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.inject.Inject;
import javax.persistence.Id;
import javax.portlet.PortletPreferences;
import java.util.ArrayList;
import java.util.List;


public class IdeationController  {
    @Inject
    @Path("index.gtmpl")
    Template indexT;

    @Inject
    PortletPreferences portletPreferences;

    @Inject
    IdeaService ideaService;

    @View
    public Response.Content index() {
        return indexT.ok();
    }
    /*@Resource(method = HttpMethod.POST)
    @Ajax
    public Response init(SecurityContext securityContext) throws JSONException {

        //--- Get context pref each time we refresh the page

        String currentUser = securityContext.getRemoteUser();
        if (currentUser == null) {
            return Response.status(401).body("You must login to create new project");
        }
        return Response.ok("{\"status\":\"ok\"}").withMimeType("application/json; charset=UTF-8").withHeader("Cache-Control", "no-cache");
    }*/

    @Ajax
    @Resource(method = HttpMethod.POST)
    public Response add(String title) throws JSONException {
        IdeaEntity cEntity = new IdeaEntity();
        cEntity.setTitle(title);
        //cEntity.setDescription(description);
        IdeaEntity cEntityPersisted = ideaService.createIdea(cEntity);
        JSONObject json = new JSONObject(cEntityPersisted);
        return Response.ok(json.toString()).withMimeType("application/json; charset=UTF-8").withHeader("Cache-Control", "no-cache");
    }


    @Resource(method = HttpMethod.POST)
    @Ajax
    public Response update(String id, String title) throws JSONException {

        //TODO should be used once
        IdeaEntity cEntity = ideaService.findIdeaById(Long.parseLong(id));
        cEntity.setTitle(title);
        //cEntity.setDescription(description);

        IdeaEntity cEntityPersisted = ideaService.updateIdea(cEntity);
        JSONObject json = new JSONObject(cEntityPersisted);

        return Response.ok(json.toString()).withMimeType("application/json; charset=UTF-8").withHeader("Cache-Control", "no-cache");
    }
/*
    @Ajax
    @Resource(method = HttpMethod.POST)
    public Response delete(String title, SecurityContext securityContext) throws JSONException {

        IdeaEntity cEntity = ideaService.findIdeaByTitle(title);
        ideaService.deleteIdea(cEntity);

        return Response.ok("{\"status\":\"ok\"}").withMimeType("application/json; charset=UTF-8").withHeader("Cache-Control", "no-cache");
    }
*/
}
