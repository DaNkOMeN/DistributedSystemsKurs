package services;

import beans.Offering;
import beans.OfferingRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
@ApplicationScoped
public class OfferingService {


    public OfferingService(){

    }

    @Transactional
    public Response addOffering(OfferingRequest request) {
        Offering offering = request.getOffering();
        if (offering != null) {
            offering.persist();
            return Response.ok().build();
        }
        return Response.notModified("Not add offering").build();
    }

    @Transactional
    public Response deleteOffering(OfferingRequest request) {
        Offering offering = request.getOffering();
        if (offering != null) {
            offering.setOffering_max_count(0);
            offering.persist();
            return Response.ok().build();
        }
        return Response.notModified("Can not delete offering").build();
    }

    @Transactional
    public Response updateOffering(OfferingRequest request) {
        Offering offering = request.getOffering();
        String offeringName = offering.getOffering_name();
        String customerName = offering.getCustomer_name();
        Offering resultOffering =  Offering.listAll().stream().map( of -> (Offering) of).filter(off -> off.getOffering_name().equals(offeringName) && off.getCustomer_name().equals(customerName)).findAny().orElse(null);
        if (resultOffering != null){
            resultOffering.setOffering_name(offering.getOffering_name());
            resultOffering.setOffering_status(offering.isOffering_status());
            resultOffering.setOffering_description(offering.getOffering_description());
            resultOffering.setOffering_max_count(offering.getOffering_max_count());
            resultOffering.persist();
            return Response.ok().build();
        }
        return Response.notModified("Not update offering").build();
    }

    @Transactional
    public Response changeStatusOffering(OfferingRequest request) {
        Offering offering = request.getOffering();
        String offeringName = offering.getOffering_name();
        String customerName = offering.getCustomer_name();
        Offering resultOffering =  Offering.listAll().stream().map( of -> (Offering) of).filter(off -> off.getOffering_name().equals(offeringName) && off.getCustomer_name().equals(customerName)).findAny().orElse(null);
        if (resultOffering != null){
            resultOffering.setOffering_status(offering.isOffering_status());
            resultOffering.persist();
            return Response.ok().build();
        }
        return Response.notModified("Status not changed").build();
    }


}
