package org.exoplatform.ideation.service.rest;

import org.exoplatform.ideation.dto.ThemeIdeaDTO;
import org.exoplatform.ideation.service.utils.ThemeIdeaService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.util.List;

@Path("/themeidea")
@Produces(MediaType.APPLICATION_JSON)
public class ThemeIdeaRestService implements ResourceContainer {
  private static Log LOG = ExoLogger.getLogger(ThemeIdeaService.class);

  @Inject
  ThemeIdeaService themeIdeaService;


  @GET
  @Path("/getallthemeidea")
  public Response getThemes() {
    try {
      List<ThemeIdeaDTO> allthemesi = themeIdeaService.GetAllTheme();
      return Response.ok(allthemesi, MediaType.APPLICATION_JSON).build();

    } catch (Exception e) {

      LOG.error("Error listing theme ", e);

      return Response.serverError()
          .entity("Error listing all theme")
          .build();
    }
  }


  @GET
  @Path("/getallthemebyidea/{id}")
  public Response getIdThemeByIdea(@PathParam("id") Long id) {
    try {
      List<ThemeIdeaDTO> allthemesi = themeIdeaService.GetAllThemeByIdIdea(id);
      return Response.ok(allthemesi, MediaType.APPLICATION_JSON).build();

    } catch (Exception e) {

      LOG.error("Error listing theme ", e);

      return Response.serverError()
          .entity("Error listing all theme")
          .build();
    }
  }

  @GET
  @Path("/getallideabytheme/{id}")
  public Response getIdIdeaByTheme(@PathParam("id") Long id) {
    try {
      List<ThemeIdeaDTO> allthemesi = themeIdeaService.GetAllIdeaByTheme(id);
      return Response.ok(allthemesi, MediaType.APPLICATION_JSON).build();

    } catch (Exception e) {

      LOG.error("Error listing theme ", e);

      return Response.serverError()
          .entity("Error listing all theme")
          .build();
    }
  }

  @POST
  @Path("/addthemeidea")
  public Response AddThemeIdea(ThemeIdeaDTO themeIdeaDTO) {
    try {
      themeIdeaDTO = themeIdeaService.AddThemeIdea(themeIdeaDTO);
      return Response.ok().entity(themeIdeaDTO).build();

    } catch (Exception e) {
      return Response.serverError()
          .entity("Error adding new theme and idea")
          .build();
    }
  }

  @DELETE
  @Path("/deletethemeidea/{id}")
  public Response DeleteTheme(@PathParam("id") Long id) {
    try {

      themeIdeaService.deleteThemeIdea(id);

      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError()
          .entity("Error delete Theme _ idea")
          .build();
    }
  }
}
