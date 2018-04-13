package rest;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.service.IdeaService;
import org.exoplatform.ideation.service.impl.IdeaServiceImpl;
import org.exoplatform.ideation.storage.dao.jpa.IdeaDAO;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Produces;

@Path("/demo")
@Produces("application/json")
public class TestRest implements ResourceContainer {
    @POST
    @Path("/hello/")
    @RolesAllowed({"administrators"})

    public String hello(IdeaEntity ideaEntity) {
        IdeaDAO ideaDAO = new IdeaDAO();
        //IdeaService ideaService = new IdeaServiceImpl(ideaDAO);
        ideaEntity.setId(1);
        ideaEntity.setTitle("hello");
      //  ideaService.createIdea(ideaEntity);
            return ideaEntity.toString();
        }

    }
