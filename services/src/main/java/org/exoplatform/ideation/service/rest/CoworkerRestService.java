package org.exoplatform.ideation.service.rest;

import org.exoplatform.ideation.dto.CowokerDTO;
import org.exoplatform.ideation.entities.CoworkerEntity;
import org.exoplatform.ideation.service.utils.CoworkerService;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/coworker")
@Produces(MediaType.APPLICATION_JSON)
public class CoworkerRestService implements ResourceContainer {
    @Inject
    CoworkerService cs;

    @GET
    @Path("/getcowoker")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public  List<CowokerDTO>   getallcowoker(){
        return cs.getallCowokerDto();
    }

    @POST
    @Path("/addcowoker")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CoworkerEntity addcowoker(CowokerDTO cr){
        return cs.addCoworker(cr);
    }

    @GET
    @Path("/getdto/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CowokerDTO select(@PathParam("id") Long id){
        return cs.getCoworker(id);
    }

}
