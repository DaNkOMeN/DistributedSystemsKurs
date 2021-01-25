package services;

import beans.Offering;
import beans.OfferingRequest;
import io.quarkus.security.identity.SecurityIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OfferingService {


    public OfferingService(){

    }

    @Transactional
    public Response addOffering(Offering offering, SecurityIdentity identity) {
        if (offering != null) {
            offering.persist();
            return Response.ok().build();
        }
        return Response.notModified("Not add offering").build();
    }

    @Transactional
    public Response deleteOffering(Offering offering) {
        if (offering != null) {
            offering.setOfferingMaxCount(0);
            offering.persist();
            return Response.ok().build();
        }
        return Response.notModified("Can not delete offering").build();
    }

    @Transactional
    public Response updateOffering(Offering offering) {
        long id = offering.id;
        Offering finded = Offering.findById(id);
        if (finded != null){
            finded.setOfferingName(offering.getOfferingName());
            finded.setOfferingStatus(offering.isOfferingStatus());
            finded.setOfferingDescription(offering.getOfferingDescription());
            finded.setOfferingMaxCount(offering.getOfferingMaxCount());
            finded.persist();
            return Response.ok().build();
        }
        return Response.notModified("Not update offering").build();
    }

    @Transactional
    public long getOfferingsCountAll(){
        return Offering.count();
    }

    @Transactional
    public long getOfferingCountByCustomer(SecurityIdentity identity) {
        return Offering.listAll().stream().map(it -> (Offering)it).filter(it -> it.getCustomerName().equals(identity.getPrincipal().getName())).count();
    }

    @Transactional
    public List<String> getResourcesTypes(){
        return Offering.listAll().stream().map(it -> ((Offering) it).getOfferingType().toString()).distinct().collect(Collectors.toList());
    }

    @Transactional
    public List<String> getAllCustomers(){
        return Offering.listAll().stream().map(it -> ((Offering) it).getCustomerName()).distinct().collect(Collectors.toList());
    }

    @Transactional
    public List<Offering> getOfferingToPage(SecurityIdentity identity, OfferingRequest request) {
        if (identity.getRoles().contains("angular-admin")) {
            String customer = request.getCustomer();
            int offeringCount = request.getOfferingCount();
            int pageNumber = request.getPageNumber();
            if (customer != null) {
                List<Offering> offerings = Offering.listAll().stream().map(off -> (Offering) off).filter(off -> off.getCustomerName().equals(customer))
                        .collect(Collectors.toList()).subList(pageNumber*offeringCount, (pageNumber + 1)*offeringCount);
                return offerings;
            } else {
                List<Offering> offerings = Offering.listAll().stream().map(off -> (Offering) off).collect(Collectors.toList())
                        .subList(pageNumber*offeringCount, (pageNumber + 1)*offeringCount);
                return offerings;
            }
        } else {
            if (identity.getRoles().contains("angular-customer")) {
                String customer = identity.getPrincipal().getName();
                int offeringCount = request.getOfferingCount();
                int pageNumber = request.getPageNumber();
                if (customer != null) {
                    List<Offering> offerings = Offering.listAll().stream().map(off -> (Offering) off).filter(off -> off.getCustomerName().equals(customer))
                            .collect(Collectors.toList()).subList(pageNumber*offeringCount, (pageNumber + 1)*offeringCount);
                    return offerings;
                }
            }
        }
        return null;
    }


}
