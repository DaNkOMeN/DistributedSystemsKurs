package org;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;


@Path("/jija")
public class TestResource {

    @Inject
    @RestClient
    TestService testService;

    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public String testRestClient() {
        return testService.testRestClient();
    }
//    @GET
//    @Path("/name/{name}")
//    public Set<Country> name(@PathParam String name) {
//        return testService.getByName(name);
//    }
}
