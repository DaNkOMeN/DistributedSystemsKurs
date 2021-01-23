package resources;


import beans.*;
import service.PriceListService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

import java.util.List;

@Path("/priceList")
@RequestScoped
public class PriceListResource {

    @Inject
    PriceListService priceListService;

    @GET
    @Path("getPriceList")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PriceListItem> getPriceList(){
       return priceListService.getPriceList();
    }

    @GET
    @Path("getPriceListAsFile")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public File getManyPriceListItem(){
        return priceListService.getPriceListAsFile();
    }


    @POST
    @Path("createOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseItem createOrder(List<OrderItem> orderItems){
        if (orderItems != null) {
            return priceListService.confirmOrder(orderItems);
        } else {
            return new ResponseItem(false, "Order items is null");
        }
    }

    @POST
    @Path("testAdd")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseItem addPriceListItem(PriceListItem item){
        return priceListService.addPriceListItem(item);
    }


    @POST
    @Path("extraCharge")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response extraChargeItems(List<ExtraChargeItem> items){
        if (items!= null || !items.isEmpty()){
            return priceListService.extraChargeItems(items);
        }
        return Response.notModified("Items is null or empty").build();
    }


    //dobavlenie elementa v korziny

    @POST
    @Path("addInBasket")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addInBasket(BasketPriceListItem basketItem){
        if (basketItem != null) {
            return priceListService.addInBasket(basketItem);
        }
        return Response.notModified("BasketItem is null").build();
    }

    @POST
    @Path("deleteInBasket")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDeleteBasket(BasketPriceListItem basketItem){
        if (basketItem != null) {
            return priceListService.deleteInBasket(basketItem);
        }
        return Response.notModified("BasketItem is null").build();
    }

    @POST
    @Path("updateInBasket")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUpdateBasket(BasketPriceListItem basketItem){
        if (basketItem != null) {
            return priceListService.updateInBasket(basketItem);
        }
        return Response.notModified("BasketItem is null").build();
    }


}
