package resources;


import beans.Offering;
import beans.OfferingRequest;
import beans.ResponseItem;
import io.quarkus.security.identity.SecurityIdentity;
import services.OfferingService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/offering")
@RequestScoped
public class OfferingResourse {

    @Inject
    OfferingService service;

    @Inject
    SecurityIdentity identity;

    public OfferingResourse() {
    }

    @POST
    @Path("getOfferingToPage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"angular-admin", "angular-customer"})
    public List<Offering> getOfferingToPage(OfferingRequest request){
        if (request != null) {
            return service.getOfferingToPage(identity, request);
        } else {
            return null;
        }
    }

    @GET
    @Path("offeringCountAll")
    @Produces(MediaType.APPLICATION_JSON)
    public long getOfferingsCountAll(){
        return service.getOfferingsCountAll();
    }

    @GET
    @Path("offeringCointByCustomer")
    @Produces(MediaType.APPLICATION_JSON)
    public long getOfferingCountByCustomer(){
        return service.getOfferingCountByCustomer(identity);
    }

    @POST
    @Path("addOffering")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "customer"})
    public Response addOffering(Offering offering) {
        if (offering != null) return service.addOffering(offering, identity);
        return Response.notModified("Request is null").build();
    }

    @POST
    @Path("deleteOffering")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "customer"})
    public Response deleteOffering(Offering offering) {
        if (offering != null) return service.deleteOffering(offering);
        return Response.notModified("Request is null").build();
    }

    @POST
    @Path("updateOffering")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "customer"})
    public Response updateOffering(Offering offering) {
        if (offering != null) return service.updateOffering(offering);
        return Response.notModified("Request is null").build();
    }


    @GET
    @Path("allCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAllCustomers(){
        return service.getAllCustomers();
    }

    @GET
    @Path("allResourseTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAllResourceTypes(){
        return service.getResourcesTypes();
    }

    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public String testRestClient(){
        return "Test proiden";
    }
}
