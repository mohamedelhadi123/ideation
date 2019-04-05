package org.exoplatform.ideation.service.rest;
import org.exoplatform.ideation.dao.IdeaImp;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.ideation.service.utils.IdeaService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import org.exoplatform.services.rest.resource.ResourceContainer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.util.List;


@Path("/idea")
@Produces(MediaType.APPLICATION_JSON)

public class IdeaRestService implements ResourceContainer {
    IdeaImp ide;

    public void setId(IdeaImp ide) {
        this.ide = ide;
    }

    private static Log log = ExoLogger.getLogger(IdeaRestService.class);

    @Inject
    IdeaService ideaService;

    @GET
    @Path("/list")
    public Response getIdeas(){

        List<IdeaEntity> ideas =ideaService.getAllIdea();
        JSONArray jsonArray=new JSONArray();
        try {
            for(IdeaEntity idea :  ideas){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id_ideas", idea.getId());
                jsonObject.put("titre", idea.getTitle());
                jsonObject.put("description", idea.getDescription());
                jsonObject.put("user", idea.getUSER());
                jsonObject.put("time", idea.getCreatedTime());
                jsonArray.add(jsonObject);

            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An internal error has occurred When trying to import categories list")
                    .build();
        }
        return Response.ok(jsonArray.toString(), MediaType.APPLICATION_JSON).build();
    }

@POST
@Path("/addidea")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

    public IdeaEntity creatIdee(IdeaEntity idee){
        return ideaService.AddIdea(idee);
}

@DELETE
@Path("/delete/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
     public String deltetidea(@PathParam("id") Long id){
        return ideaService.deleteIdea(id);
}


}
