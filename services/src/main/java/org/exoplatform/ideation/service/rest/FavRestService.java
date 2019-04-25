package org.exoplatform.ideation.service.rest;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dto.FavoritDTO;
import org.exoplatform.ideation.entities.FavoriteEntity;
import org.exoplatform.ideation.service.utils.FavService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/fav")
@Produces(MediaType.APPLICATION_JSON)
public class FavRestService implements ResourceContainer {
    private static Log LOG = ExoLogger.getLogger(FavoriteEntity.class);
    @Inject
    FavService favService;

    public FavRestService() {
        favService= CommonsUtils.getService(FavService.class);
    }

    @GET
    @Path("/getfavuser/{user}")
    public Response getallfavByUser(@PathParam("user") String user){
    try {
        List<FavoritDTO> allFavByUser=favService.getAllFavByUser(user);
        return Response.ok(allFavByUser, MediaType.APPLICATION_JSON).build();


    }catch (Exception e){
        LOG.error("Error listing all fav ", e);
        return Response.serverError()
                .entity("Error listing all fav")
                .build();

    }
    }

@POST
@Path("/addfav")
public Response addFav(FavoritDTO favoritDTO){
        try{
            favoritDTO=favService.addFav(favoritDTO);
            return Response.ok().entity(favoritDTO).build();
        }catch (Exception e) {
            return Response.serverError()
                    .entity("Error adding new comment")
                    .build();
        }
}


}




