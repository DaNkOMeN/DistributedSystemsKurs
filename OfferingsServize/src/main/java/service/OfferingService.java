package service;

import beans.CheatForType;
import beans.Filter;
import beans.Offering;
import beans.OfferingRequest;
import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.jandex.Index;

import javax.annotation.processing.Filer;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class OfferingService {


    public OfferingService() {

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
        if (founded != null) {
            founded.setOfferingName(offering.getOfferingName());
            founded.setOfferingStatus(offering.isOfferingStatus());
            founded.setOfferingDescription(offering.getOfferingDescription());
            founded.setOfferingMaxCount(offering.getOfferingMaxCount());
            founded.setOfferingType(offering.getOfferingType());
            founded.setOfferingPrice(offering.getOfferingPrice());
            founded.persist();
            return Response.ok().build();
        }
        return Response.notModified("Not update offering").build();
    }

    @Transactional
    public long getOfferingsCountAll() {
        return Offering.count();
    }

    @Transactional
    public long getOfferingCountByCustomer(SecurityIdentity identity) {
        return Offering.listAll().stream().map(it -> (Offering) it).filter(it -> it.getCustomerName().equals(identity.getPrincipal().getName())).count();
    }

    @Transactional
    public List<String> getAllCustomers() {
        return Offering.listAll().stream().map(it -> ((Offering) it).getCustomerName()).distinct().collect(Collectors.toList());
    }


    @Transactional
    public List<Offering> getOfferingToPageWithCheat(SecurityIdentity identity, OfferingRequest request) {
        List<Offering> allOfferings = new ArrayList<>();
        for (Offering jopa : getOfferingToPage(identity, request)) {
            if (jopa.getOfferingMaxCount() >= 1) {
                allOfferings.add(new Offering(jopa));
            }
        }
        return prepareForCheating(allOfferings);
    }


    @Transactional
    private List<Offering> prepareForCheating(List<Offering> preparedOfferings) {
        List<Offering> result = new ArrayList<>(preparedOfferings);
        List<CheatForType> cheats = CheatForType.listAll().stream().map(ch -> (CheatForType) ch).collect(Collectors.toList());
        for (Offering off : result) {
            for (CheatForType cheat : cheats) {
                if (Objects.equals(cheat.getOfferingType(), off.getOfferingType())) {
                    off.setOfferingPrice(off.getOfferingPrice() + off.getOfferingPrice() * cheat.getCheatPercent() / 100);
                }
            }
        }
        return result;
    }

    @Transactional
    public List<Offering> getFilteredOffering(Filter filter) {
        String offeringType = filter.getOfferingType();
        String customerName = filter.getCustomerName();
        List<Offering> allOfferings = Offering.listAll().stream().map(off -> (Offering) off).collect(Collectors.toList());
        if (offeringType != null && !offeringType.isEmpty() && customerName != null && !customerName.isEmpty()) {
            return allOfferings.stream().filter(off -> Objects.equals(off.getOfferingType(), offeringType) && Objects.equals(off.getCustomerName(), customerName)).collect(Collectors.toList());
        } else if ((offeringType == null || offeringType.isEmpty()) && (customerName != null && !customerName.isEmpty())) {
            return allOfferings.stream().filter(off -> Objects.equals(off.getCustomerName(), customerName)).collect(Collectors.toList());
        } else if ((customerName == null || customerName.isEmpty()) && (offeringType != null && !offeringType.isEmpty())) {
            return allOfferings.stream().filter(off -> Objects.equals(off.getOfferingType(), offeringType)).collect(Collectors.toList());
        } else return allOfferings;
    }

    @Transactional
    public List<Offering> getOfferingToPage(SecurityIdentity identity, OfferingRequest request) {
        Filter filter = new Filter(request.getCustomer(), request.getOfferingType());
        List<Offering> offeringList = getFilteredOffering(filter);
        int pageNumber = request.getPageNumber();
        int offeringCount = request.getOfferingCount();
        try {
            return offeringList.subList(pageNumber * offeringCount, (pageNumber + 1) * offeringCount);
        } catch (IndexOutOfBoundsException ex) {
            int count = offeringList.size();
            if (count <= pageNumber * offeringCount) return null;
            else return offeringList.subList(pageNumber * offeringCount, count - pageNumber * offeringCount);
        }
    }

    @Transactional
    public Response addCheating(CheatForType cheat) {
        CheatForType cheatForType = CheatForType.listAll().stream().map(ch -> (CheatForType) ch).filter(ch -> ch.getOfferingType().equals(cheat.getOfferingType())).findAny().orElse(null);
        if (cheatForType != null) {
            cheatForType.setCheatPercent(cheat.getCheatPercent());
            cheatForType.persist();
        } else {
            cheat.persist();
        }
        return Response.ok().build();
    }

    @Transactional
    public List<Offering> getAllOfferings() {
        return prepareForCheating(Offering.listAll().stream().map(off -> (Offering) off).filter(offer -> offer.getOfferingMaxCount() > 0).collect(Collectors.toList()));
    }
}
