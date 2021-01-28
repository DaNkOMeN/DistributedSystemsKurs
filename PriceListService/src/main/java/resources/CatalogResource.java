package resources;


import beans.*;
import io.quarkus.security.identity.SecurityIdentity;
import service.CatalogService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/priceList")
@RequestScoped
public class CatalogResource {

    @Inject
    SecurityIdentity identity;

    @Inject
    CatalogService catalogService;

    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Offering hello() {
        return new Offering();
    }

    @GET
    @Path("getOfferingToPageWithCheat")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("angular-admin")
    public List<Offering> getOfferingToPageWithCheat() {
        return catalogService.getOfferingToPageWithCheat();
    }

    @GET
    @Path("getOfferingsFromBD")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("angular-buyer")
    public List<Offering> getOfferingFromBD() {
        return catalogService.getOfferingsFromBD();
    }

    @GET
    @Path("getTotalByBuyer")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("angular-buyer")
    public BasketTotal getBasketTotalByBuyer() {
        return catalogService.getBasketTotalByBuyer(identity);
    }

    @POST
    @Path("addItemToBasket")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("angular-buyer")
    public BasketTotal addItemToBasket(Offering offering) {
        return catalogService.addItemToBasket(offering, identity.getPrincipal().getName());
    }

    @POST
    @Path("updateItemInBasket")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("angular-buyer")
    public BasketTotal updateItemInBasket(BasketItem item) {
        return catalogService.updateItemInBasket(item, identity);
    }

    @POST
    @Path("deleteItemFromBasket")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("angular-buyer")
    public BasketTotal deleteItemFromBasket(BasketItem basketItem) {
        return catalogService.deleteItemFromBasket(basketItem);
    }

    @GET
    @Path("getBuyersBasket")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("angular-buyer")
    public List<BasketItem> getBuyersBasket() {
        return catalogService.getBuyersBasket(identity.getPrincipal().getName());
    }

    @GET
    @Path("allCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAllCustomers(){
        return catalogService.getAllCustomers();
    }

    @GET
    @Path("allResourseTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAllResourceTypes(){
        return new ArrayList<String>(){{ add("Звезда"); add("Место"); add("Услуга"); add("Машина");add("Укрошение");}};
    }

}
