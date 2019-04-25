package org.exoplatform.ideation.service.rest;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dto.RatingDTO;
import org.exoplatform.ideation.entities.RatingEntity;
import org.exoplatform.ideation.service.utils.RatingService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rating")
@Produces(MediaType.APPLICATION_JSON)
public class RatingRestService implements ResourceContainer {
    private static Log LOG = ExoLogger.getLogger(RatingService.class);
    @Inject
    RatingService ratingservice;

    public RatingRestService() {
        ratingservice= CommonsUtils.getService(RatingService.class);
    }
    @GET
    @Path("getratingbyidea/{id}/{DISLIKE}")
    public Response getAllRatingByIdeaIdAndStatus(@PathParam("id") Long id,@PathParam("DISLIKE")RatingEntity.Status DISLIKE){
        try{
            List<RatingDTO> allRatingByIdea=ratingservice.getStatusByIdeaAndStatus(id,DISLIKE);
            return Response.ok(allRatingByIdea, MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {

            LOG.error("Error listing Rating ", e);

            return Response.serverError()
                    .entity("Error listing all Rating")
                    .build();
        }
    }
    @POST
    @Path("/addRating")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRating(RatingDTO ratingDTO){
        try {
            ratingDTO=ratingservice.addRating(ratingDTO);
            return Response.ok().entity(ratingDTO).build();

        }catch (Exception e) {
            return Response.serverError()
                    .entity("Error adding new rating")
                    .build();
        }
    }






}
