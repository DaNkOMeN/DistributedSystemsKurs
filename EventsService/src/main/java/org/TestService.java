package org;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/offering")
@RegisterRestClient(configKey = "test")
public interface TestService {

    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public String testRestClient();

//    @GET
//    @Path("/name/{name}")
//    Set<Country> getByName(@PathParam String name);
}
