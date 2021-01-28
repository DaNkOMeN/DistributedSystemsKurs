package service;


import beans.Offering;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/offering")
@RegisterRestClient(configKey = "offering")
public interface OfferingService {

    @GET
    @Path("getAllOfferings")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("angular-admin")
    List<Offering> getAllOffering();

}
