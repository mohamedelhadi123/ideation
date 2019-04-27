package org.exoplatform.ideation.service.rest;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dto.IdeaDTO;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.ideation.service.utils.IdeaService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/idea")
@Produces(MediaType.APPLICATION_JSON)

public class IdeaRestService implements ResourceContainer {



    private static Log LOG = ExoLogger.getLogger(IdeaRestService.class);

    @Inject
    IdeaService ideaService;

    public IdeaRestService() {
        ideaService= CommonsUtils.getService(IdeaService.class);
    }

    @GET
    @Path("/all/{PUBLISHED}")
    public Response getAllIdeaPublished(@PathParam("PUBLISHED") IdeaEntity.Status PUBLISHED) {


        try {
            List<IdeaDTO> allIdeaPublished = ideaService.getIdeaPublished(PUBLISHED);

            return Response.ok(allIdeaPublished, MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {

            LOG.error("Error listing all Idea Published ", e);

            return Response.serverError()
                    .entity("Error listing all Idea Published")
                    .build();
        }
    }
   @GET
   @Path("/getone/{id}")
   public Response getone(@PathParam("id") Long id){
        try {
            IdeaDTO findoneidea=ideaService.getIdea(id);
            return Response.ok(findoneidea, MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {

            LOG.error("Error listing all Idea Published ", e);

            return Response.serverError()
                    .entity("Error listing all Idea Published")
                    .build();
        }
   }
    @GET
    @Path("allpublishedbyuser/{PUBLISHED}/{user}")
    public Response getAllPublishedByUser(@PathParam("PUBLISHED") IdeaEntity.Status PUBLISHED,@PathParam("user") String user) {
        try {
            List<IdeaDTO> allIdeaPublishedByUser = ideaService.getIdeaPublishedByUser(PUBLISHED, user);
            return Response.ok(allIdeaPublishedByUser, MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {

            LOG.error("Error listing all Idea Published by user ", e);

            return Response.serverError()
                    .entity("Error listing all Idea Published user")
                    .build();
        }
    }
    @POST
    @Path("addIdea")
    public  Response addIdea(IdeaDTO ideaDTO){
        try{
            ideaDTO=ideaService.addIdea(ideaDTO);
            return Response.ok().entity(ideaDTO).build();
        }catch (Exception e) {
            return Response.serverError()
                    .entity("Error adding new idea")
                    .build();
        }
    }





}
