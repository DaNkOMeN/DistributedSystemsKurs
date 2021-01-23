package resources;


import beans.Offering;
import beans.OfferingRequest;
import beans.ResponseItem;
import services.OfferingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/offering")
@RequestScoped
public class OfferingResourse {

    @Inject
    OfferingService service;

    public OfferingResourse() {
    }


    @POST
    @Path("addOffering")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOffering(OfferingRequest request) {
        if (request != null) return service.addOffering(request);
        return Response.notModified("Request is null").build();
    }

    @POST
    @Path("deleteOffering")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOffering(OfferingRequest request) {
        if (request != null) return service.deleteOffering(request);
        return Response.notModified("Request is null").build();
    }

    @POST
    @Path("updateOffering")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOffering(OfferingRequest request) {
        if (request != null) return service.updateOffering(request);
        return Response.notModified("Request is null").build();
    }

    @POST
    @Path("changeStatusOffering")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeStatusOffering(OfferingRequest request) {
        if (request != null) return service.changeStatusOffering(request);
        return Response.notModified("Request is null").build();
    }


    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public String testRestClient(){
        return "Test proiden";
    }
}
