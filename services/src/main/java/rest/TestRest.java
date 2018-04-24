package rest;
import org.exoplatform.ideation.entities.domain.CommentEntity;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.ideation.service.impl.IdeaServiceImpl;
import org.exoplatform.ideation.storage.dao.jpa.CommentDAO;
import org.exoplatform.ideation.storage.dao.jpa.IdeaDAO;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Produces;

@Path("/demo")
@Produces("application/json")
public class TestRest implements ResourceContainer {
    @GET
    @Path("/hello/")
    public CommentEntity hello(CommentEntity ideaEntity) {
        ideaEntity.setCommentText("hello");
        ideaEntity.setAuthor("root");
        ideaEntity.setId(ideaEntity.getId());
       // ideaEntity.setIdeaId(ideaEntity.getIdeaId());
        System.out.println(ideaEntity.getCommentText());
            return ideaEntity;
        }
}
