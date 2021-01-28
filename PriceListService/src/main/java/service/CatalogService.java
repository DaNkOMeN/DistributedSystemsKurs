package service;

import beans.*;
import io.quarkus.security.identity.SecurityIdentity;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class CatalogService {


    @Inject
    @RestClient
    OfferingService offeringService;


    @Transactional
    public List<Offering> getOfferingToPageWithCheat() {
        List<Offering> offeringsFromService = offeringService.getAllOffering();
        Offering.deleteAll();
        for (Offering off: offeringsFromService) {
            off.id = null;
            off.persist();
        }
        return offeringsFromService;
    }

    @Transactional
    public List<Offering> getOfferingsFromBD() {
       return Offering.listAll().stream().map(off -> (Offering)off).collect(Collectors.toList());
    }

    @Transactional
    public BasketTotal addItemToBasket(Offering offering, String buyerName) {
        List<BasketItem> buyersItem = BasketItem.listAll().stream().map(item -> (BasketItem)item).filter(item -> Objects.equals(item.getBuyer(), buyerName)).collect(Collectors.toList());
        BasketItem foundedItem = buyersItem.stream().filter(item -> offeringInBasket(offering, item)).findFirst().orElse(null);
        if (foundedItem != null) {
            foundedItem.setCount(foundedItem.getCount() + 1);
            foundedItem.persist();
        } else {
            BasketItem newItem = new BasketItem(offering, buyerName);
            newItem.persist();
        }
        BasketTotal bt = new BasketTotal(buyerName);
        return bt;
    }

    @Transactional
    public List<BasketItem> getBuyersBasket(String buyerName){
        return BasketItem.listAll().stream().map(item -> (BasketItem)item).filter(item -> Objects.equals(item.getBuyer(), buyerName)).collect(Collectors.toList());
    }

    @Transactional
    public BasketTotal deleteItemFromBasket(BasketItem basketItem) {
        String buyerName = basketItem.getBuyer();
        BasketItem.deleteById(basketItem.id);
        return new BasketTotal(buyerName);
    }

    @Transactional
    public List<String> getAllCustomers() {
        return Offering.listAll().stream().map(it -> ((Offering) it).getCustomerName()).distinct().collect(Collectors.toList());
    }

    public static boolean offeringInBasket(Offering offering, BasketItem basketItem){
        return Objects.equals(offering.getCustomerName(), basketItem.getCustomerName()) &&
                Objects.equals(offering.getOfferingName(), basketItem.getOfferingName()) &&
                Objects.equals(offering.getOfferingPrice(), basketItem.getOfferingPrice()) &&
                Objects.equals(offering.isOfferingStatus(), basketItem.isOfferingStatus()) &&
                Objects.equals(offering.getOfferingDescription(), basketItem.getOfferingDescription()) &&
                Objects.equals(offering.getOfferingMaxCount(), basketItem.getOfferingMaxCount());
    }


    @Transactional
    public BasketTotal getBasketTotalByBuyer(SecurityIdentity identity) {
        return new BasketTotal(identity.getPrincipal().getName());
    }

    @Transactional
    public BasketTotal updateItemInBasket(BasketItem item, SecurityIdentity identity ) {
        if (item.getCount() == 0) {
            return deleteItemFromBasket(item);
        }
        BasketItem basketItem = BasketItem.findById(item.id);
        if (item != null) {
            basketItem.setCount(item.getCount());
            basketItem.persist();
            return new BasketTotal(identity.getPrincipal().getName());
        } else {
            return null;
        }
    }
}
