package service;


import beans.PriceListItem;
import beans.ResourceType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/priceList")
@Produces(MediaType.APPLICATION_JSON)
public class PriceListResource {

    List<PriceListItem> itemsList = new ArrayList<>();

    public PriceListResource(){
        PriceListItem first = new PriceListItem(1, "Jija", 10, 50, ResourceType.SPACE, "Lol");
        PriceListItem second = new PriceListItem(1, "Prejija", 20, 60, ResourceType.COSTUME, "kek");
        PriceListItem thirth = new PriceListItem(1, "Perjija", 30, 70, ResourceType.DECORATION, "Chebureck");
        itemsList.add(first);
        itemsList.add(second);
        itemsList.add(thirth);
    }

    @GET
    @Path("/single")
    public PriceListItem getPriceListItem(){
        return new PriceListItem(1, "Jija", 10, 10, ResourceType.SPACE, "Jija perjija");
    }

    @GET
    @Path("/many")
    public List<PriceListItem> getManyPriceListItem(){

        return itemsList;

    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<PriceListItem> addPriceListItem(PriceListItem item){
        itemsList.add(item);
        return itemsList;
    }

}
