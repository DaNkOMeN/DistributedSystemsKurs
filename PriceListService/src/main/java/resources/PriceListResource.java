package resources;


import beans.PriceListItem;
import beans.ResourceType;
import service.PriceListService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Path("/priceList")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PriceListResource {

    @Inject
    PriceListService priceListService;

    @GET
    @Path("single")
    public PriceListItem getPriceListItem(){
        return new PriceListItem(1, "Jija", 10, 10, ResourceType.SPACE, "Jija perjija");
    }

    @GET
    @Path("many")
    public List<PriceListItem> getManyPriceListItem(){
        return priceListService.getPriceListItemList();

    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<PriceListItem> addPriceListItem(PriceListItem item){
        if (item.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        item.persist();
        return PriceListItem.listAll();
    }

    @GET
    @Path("priceListAsFile")
    public File getPriceListAsFile(){
        return new File("Asd");
    }

}
