package service;

import beans.Filter;
import beans.Offering;
import beans.OfferingRequest;
import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.jandex.Index;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class OfferingService {


    public OfferingService(){

    }

    @Transactional
    public Response addOffering(Offering offering, SecurityIdentity identity) {
        if (offering != null) {
            offering.setCustomerName(identity.getPrincipal().getName());
            offering.persist();
            return Response.ok().build();
        }
        return Response.notModified("Not add offering").build();
    }

    @Transactional
    public Response deleteOffering(Offering offering) {
        if (offering != null) {
            Offering founded = Offering.findById(offering.id);
            founded.setOfferingMaxCount(0);
            founded.persist();
            return Response.ok().build();
        }
        return Response.notModified("Can not delete offering").build();
    }

    @Transactional
    public Response updateOffering(Offering offering) {
        long id = offering.id;
        Offering founded = Offering.findById(id);
        if (founded != null){
            founded.setOfferingName(offering.getOfferingName());
            founded.setOfferingStatus(offering.isOfferingStatus());
            founded.setOfferingDescription(offering.getOfferingDescription());
            founded.setOfferingMaxCount(offering.getOfferingMaxCount());
            founded.setOfferingType(offering.getOfferingType());
            founded.persist();
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
                return getOfferings(customer, offeringCount, pageNumber);
            } else {
                try {
                    return Offering.listAll().stream().map(off -> (Offering) off).collect(Collectors.toList())
                            .subList(pageNumber * offeringCount, (pageNumber + 1) * offeringCount);
                } catch (IndexOutOfBoundsException ex) {
                    List<Offering> offerings = Offering.listAll().stream().map(off -> (Offering) off).collect(Collectors.toList());
                    int count = offerings.size();
                    if (count <= pageNumber * offeringCount) return null;
                    else return offerings.subList(pageNumber * offeringCount, count - pageNumber * offeringCount);
                }
            }
        } else {
            if (identity.getRoles().contains("angular-customer")) {
                String customer = identity.getPrincipal().getName();
                int offeringCount = request.getOfferingCount();
                int pageNumber = request.getPageNumber();
                if (customer != null) {

                    return getOfferings(customer, offeringCount, pageNumber);
                }
            }
        }
        return null;
    }

    private List<Offering> getOfferings(String customer, int offeringCount, int pageNumber) {
        try {
            return Offering.listAll().stream().map(off -> (Offering) off).filter(off -> off.getCustomerName().equals(customer))
                    .collect(Collectors.toList()).subList(pageNumber * offeringCount, (pageNumber + 1) * offeringCount);
        } catch (IndexOutOfBoundsException ex) {
            List<Offering> offerings = Offering.listAll().stream().map(off -> (Offering) off).filter(off -> off.getCustomerName().equals(customer))
                    .collect(Collectors.toList());
            int count = offerings.size();
            if (count <= pageNumber * offeringCount) return null;
            else return offerings.subList(pageNumber * offeringCount, count - pageNumber * offeringCount);
        }
    }

    @Transactional
    public List<Offering> getFilteredOffering(Filter filter) {
        String offeringType = filter.getOfferingType();
        String customerName = filter.getCustomerName();
        List<Offering> allOfferings = Offering.listAll().stream().map(off -> (Offering) off).collect(Collectors.toList());
        if (offeringType != null && !offeringType.isEmpty() && customerName != null && !customerName.isEmpty()) {
            return allOfferings.stream().filter(off -> Objects.equals(off.getOfferingType(), offeringType) && Objects.equals(off.getCustomerName(), customerName)).collect(Collectors.toList());
        } else if (offeringType == null || offeringType.isEmpty()) {
            return allOfferings.stream().filter(off -> Objects.equals(off.getCustomerName(), customerName)).collect(Collectors.toList());
        } else
            return allOfferings.stream().filter(off -> Objects.equals(off.getOfferingType(), offeringType)).collect(Collectors.toList());
    }
}
