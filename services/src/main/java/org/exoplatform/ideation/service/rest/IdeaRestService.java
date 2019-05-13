package org.exoplatform.ideation.service.rest;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dto.IdeaDTO;
import org.exoplatform.ideation.entities.IdeaEntity;
import org.exoplatform.ideation.service.utils.IdeaService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;
import org.exoplatform.social.core.manager.IdentityManager;
import org.exoplatform.social.core.identity.model.Identity;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Path("/idea")
@Produces(MediaType.APPLICATION_JSON)

public class IdeaRestService implements ResourceContainer {


    protected IdentityManager identityManager = null;

    private static Log LOG = ExoLogger.getLogger(IdeaRestService.class);

    @Inject
    IdeaService ideaService;

    public IdeaRestService() {
        ideaService= CommonsUtils.getService(IdeaService.class);
        identityManager = CommonsUtils.getService(IdentityManager.class);
    }

    @GET
    @Path("/all/{status}")
    public Response getAllIdeaPublished(@PathParam("status") IdeaEntity.Status status) {


        try {
            String pattern = "yyyy-mm-dd hh:mm:ss";
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("fr", "FR"));
            String date = simpleDateFormat.format(new Date());
            System.out.println(date);
            List<IdeaDTO> allIdeaPublished = ideaService.getIdeaByStatus(status);
            String authenticatedUser = ConversationState.getCurrent().getIdentity().getUserId();

            Identity currentUser = CommonsUtils.getService(IdentityManager.class).getOrCreateIdentity(OrganizationIdentityProvider.NAME, authenticatedUser, true);
            return Response.ok(allIdeaPublished, MediaType.APPLICATION_JSON).build();



        } catch (Exception e) {

            LOG.error("Error listing all Idea Published ", e);

            return Response.serverError()
                    .entity("Error listing all Idea Published")
                    .build();
        }
    }
    @GET
    @Path("/getideabyid/{id}")
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
    @Path("AllIdeaByUserAndStatus/{status}")
    public Response getAllPublishedByUser(@PathParam("status") IdeaEntity.Status status) {
        try {
            String user = ConversationState.getCurrent().getIdentity().getUserId();


            List<IdeaDTO> allIdeaPublishedByUser = ideaService.getIdeaByUserAndStatus(status,user);
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


    @DELETE
    @Path("/delete/{id}")
    public  Response deltetidea(@PathParam("id") Long id ) {
        try {

            ideaService.deleteIdea(id);

            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("Error delete idea")
                    .build();

        }


    }
    @PUT
    @Path("/update")
    public Response updateIdea(IdeaDTO ideaDTO) {
        try {

            ideaService.updateIdea(ideaDTO);

            return Response.ok().entity(ideaDTO).build();

        } catch (Exception e) {

            LOG.error("Error updating idea {} by {} ", e);

            return Response.serverError().build();

        }

    }


}
