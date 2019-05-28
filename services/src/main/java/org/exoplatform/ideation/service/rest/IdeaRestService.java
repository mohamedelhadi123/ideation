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
import org.json.simple.JSONObject;


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
    ideaService = CommonsUtils.getService(IdeaService.class);
    identityManager = CommonsUtils.getService(IdentityManager.class);
  }

  @GET
  @Path("/all/{status}")
  public Response getAllIdeaPublished(@PathParam("status") IdeaEntity.Status status) {


    try {
      List<IdeaDTO> allIdeaPublished = ideaService.getIdeaByStatus(status);
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
  public Response getone(@PathParam("id") Long id) {
    try {
      IdeaDTO idea = ideaService.getIdea(id);
      return Response.ok(idea, MediaType.APPLICATION_JSON).build();

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
      List<IdeaDTO> allIdeaPublishedByUser = ideaService.getIdeaByUserAndStatus(status, getCurrentUser());
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
  public Response addIdea(IdeaDTO ideaDTO) {
    try {
      ideaDTO = ideaService.addIdea(ideaDTO);
      return Response.ok().entity(ideaDTO).build();
    } catch (Exception e) {
      return Response.serverError()
          .entity("Error adding new idea")
          .build();
    }
  }


  @DELETE
  @Path("/delete/{id}")
  public Response deltetidea(@PathParam("id") Long id) {
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
      LOG.error("Error updating idea {} by {} ", ideaDTO.getId(), getCurrentUser(),e);
      return Response.serverError().build();
    }
  }


  @POST
  @Path("/addSapace/{ideaID}")
  public Response AddSapce(@PathParam("ideaID") Long ideaID) throws Exception {
    if(getCurrentUser() ==null) {
      return Response.status(Response.Status.FORBIDDEN).build();
    }
    if(ideaID == null){
      return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
    ideaService.createSpace(ideaID, getCurrentUser());
    return Response.status(Response.Status.OK).entity("Create space").build();
  }

  @GET
  @Path("/geturl/{spaceID}")

  public Response GetUrlSpace(@PathParam("spaceID") String spaceID) {
    try {
      String url = ideaService.GetUrlSpace(spaceID);
      JSONObject jo = new JSONObject();
      jo.put("url", url);
      return Response.ok(jo).build();
    } catch (Exception e) {
      LOG.error("Erreur to URL SPACE" + e.getMessage());
      return Response.serverError().entity("Error URL SPACE").build();
    }


  }

  String getCurrentUser () {
    org.exoplatform.services.security.Identity currentIdentity = ConversationState.getCurrent().getIdentity();
    if(currentIdentity != null){
      return currentIdentity.getUserId();
    }
    return null;
  }


}
