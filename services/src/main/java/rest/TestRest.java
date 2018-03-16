package rest;
import org.apache.ecs.wml.I;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.ideation.storage.dao.IdeaDAO;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("/demo")

@Produces("application/json")
public class TestRest implements ResourceContainer {
    @GET
    @Path("/hello/{title}")
    @RolesAllowed({"administrators"})

    public String hello(@PathParam("title") String title) {
        IdeaEntity ideaEntity = new IdeaEntity() ;
        ideaEntity.setTitle("helloooo");
        IdeaDAO ideaDAO = new IdeaDAO();
        ideaEntity = ideaDAO.create(ideaEntity);
            return "Hello " + ideaEntity.toString();

        }
}
