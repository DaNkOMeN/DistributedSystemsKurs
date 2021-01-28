package resources;


import beans.BasketItem;
import beans.Order;
import beans.TotalcostCustomer;
import service.EventsService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/events")
@RequestScoped
public class EventsResourse {

    @Inject
    EventsService eventService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addToHistory")
    public Response addToHistory(List<BasketItem> basketItems){
        if (basketItems != null) {
            return eventService.addToHistory(basketItems);
        } else {
            return Response.notModified("Cant add basket items").build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("filterHistory")
    public List<TotalcostCustomer> filterHistory(String filter) {
        return eventService.filterBasketItemsByYearAndMonth(filter);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("filterOrder")
    public List<Order> getFilteredOrder(String filter) {
        return eventService.getFilteredOrder(filter);
    }

}
