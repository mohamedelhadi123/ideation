package org.exoplatform.ideation.service.rest;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dto.FavoritDTO;
import org.exoplatform.ideation.dto.OrderIdeaDTO;
import org.exoplatform.ideation.entities.FavoriteEntity;
import org.exoplatform.ideation.service.utils.FavService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/fav")
@Produces(MediaType.APPLICATION_JSON)
public class FavRestService implements ResourceContainer {
    protected IdentityManager identityManager = null;

    private static Log LOG = ExoLogger.getLogger(FavoriteEntity.class);
    @Inject
    FavService favService;

    public FavRestService() {
        favService= CommonsUtils.getService(FavService.class);
        identityManager = CommonsUtils.getService(IdentityManager.class);

    }

    @GET
    @Path("/getfavbyuser")
    public Response getallfavByUser(){
        try {
            String user = ConversationState.getCurrent().getIdentity().getUserId();

            List<FavoritDTO> allFavByUser=favService.getAllFavByUser(user);
            return Response.ok(allFavByUser, MediaType.APPLICATION_JSON).build();


        }catch (Exception e){
            LOG.error("Error listing all fav ", e);
            return Response.serverError()
                    .entity("Error listing all fav")
                    .build();

        }
    }
    @GET@Path("/verif/{id}")
    public Response getVerif( @PathParam("id") Long id) {
        try {
            String user = ConversationState.getCurrent().getIdentity().getUserId();

            List<FavoritDTO> allFavByUser = favService.getallbyIdeaANDuser(user, id);
            return Response.ok(allFavByUser, MediaType.APPLICATION_JSON).build();


        } catch (Exception e) {
            LOG.error("Error listing all fav ", e);
            return Response.serverError()
                    .entity("Error listing all fav")
                    .build();
        }
    }

    @GET@Path("/getbyidea/{id}")
    public Response getByIdea(@PathParam("id") Long id){
        try { List<FavoritDTO> allFavByUser = favService.getallbyidea(id);
            return Response.ok(allFavByUser, MediaType.APPLICATION_JSON).build();


        } catch (Exception e) {
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

    @GET
    @Path("/getallordered")
    public Response getallordered(){
        try {
            List<OrderIdeaDTO>allordered=favService.getAllOrderByFav();
            return Response.ok(allordered, MediaType.APPLICATION_JSON).build();


        }catch (Exception e){
            LOG.error("Error listing all fav ", e);
            return Response.serverError()
                    .entity("Error listing all fav")
                    .build();

        }
    }
}




