package org.exoplatform.ideation.service.rest;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ideation.dto.CommentDTO;
import org.exoplatform.ideation.entities.CommentEntity;
import org.exoplatform.ideation.service.utils.CommentService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/comment")
@Produces(MediaType.APPLICATION_JSON)
public class CommentRestService implements ResourceContainer {

    private static Log LOG = ExoLogger.getLogger(CommentEntity.class);

    @Inject
    CommentService commentservice;

    public CommentRestService() {
        commentservice= CommonsUtils.getService(CommentService.class);
    }

    @POST
    @Path("/add")
    public Response addComment(CommentDTO commentDTO) {
        try {
            commentDTO=commentservice.addComment(commentDTO);
            return Response.ok().entity(commentDTO).build();


        } catch (Exception e) {
            return Response.serverError()
                    .entity("Error adding new comment")
                    .build();
        }


    }


    @GET
    @Path("/allcommentbyidea/{id}")
    public  Response getallcommentByIdea(@PathParam("id")  Long id){
        try{
            List<CommentDTO> allCommentByIdea=commentservice.getAllCommentByIdea(id);
            return Response.ok(allCommentByIdea, MediaType.APPLICATION_JSON).build();

        }catch (Exception e){
            LOG.error("Error listing all Idea Published ", e);
            return Response.serverError()
                    .entity("Error listing all comment")
                    .build();

        }
    }




}
