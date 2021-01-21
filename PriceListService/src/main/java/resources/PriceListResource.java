package resources;


import beans.OrderItem;
import beans.PriceListItem;
import beans.ResourceType;
import beans.ResponseItem;
import service.PriceListService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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


}
