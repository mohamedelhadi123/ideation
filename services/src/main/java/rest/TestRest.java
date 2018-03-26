/*package rest;
import org.apache.ecs.wml.I;
import org.apache.poi.ss.formula.functions.Now;
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
import java.util.Set;

@Path("/demo")

@Produces("application/json")
public class TestRest implements ResourceContainer {
    @GET
    @Path("/hello/")
    @RolesAllowed({"administrators"})

    public String hello(  List<IdeaEntity> title) {
        IdeaEntity ideaEntity = new IdeaEntity() ;
        IdeaDAO ideaDAO = new IdeaDAO();
        ideaEntity.setId(1);
        ideaEntity.setTitle("hello");
        ideaEntity.setStatus("status");
        ideaEntity.setCreatedBy("adel");
        title = ideaDAO.getAllIdeas();
            return title.toString();

        }

    }
*/