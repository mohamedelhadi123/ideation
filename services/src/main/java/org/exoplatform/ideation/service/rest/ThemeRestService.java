package org.exoplatform.ideation.service.rest;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dto.ThemeDTO;
import org.exoplatform.ideation.service.utils.ThemeService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/theme")
@Produces(MediaType.APPLICATION_JSON)
public class ThemeRestService implements ResourceContainer {
    private static Log LOG = ExoLogger.getLogger(ThemeService.class);
    @Inject
    ThemeService themeService;

    public ThemeRestService() {
        themeService= CommonsUtils.getService(ThemeService.class);
    }
    @GET
    @Path("/getalltheme")
    public Response getAllTheme(){
        try {
            List<ThemeDTO> allTheme=themeService.getallTheme();
            return Response.ok(allTheme, MediaType.APPLICATION_JSON).build();


        } catch (Exception e) {

            LOG.error("Error listing theme ", e);

            return Response.serverError()
                    .entity("Error listing all theme")
                    .build();
        }
    }
    @GET
    @Path("/getthemebyid/{id}")
    public Response GetThemeById(@PathParam("id") Long id){
        try {
            ThemeDTO themeDTO=themeService.getThemeById(id);
            return Response.ok(themeDTO, MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {

            LOG.error("Error listing theme ", e);

            return Response.serverError()
                    .entity("Error listing  theme by id")
                    .build();
        }
    }

    @POST
    @Path("addtheme")
    public Response AddTheme(ThemeDTO themeDTO){
        try {
            themeDTO=themeService.addTheme(themeDTO);
            return Response.ok().entity(themeDTO).build();


        }catch (Exception e) {
            return Response.serverError()
                    .entity("Error adding new idea")
                    .build();
        }
    }


}
