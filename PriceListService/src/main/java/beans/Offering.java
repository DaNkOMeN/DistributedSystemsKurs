package beans;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Offering extends PanacheEntity {

    private String customerName;
    private String offeringName;
    private String offeringDescription;
    private int offeringMaxCount;
    private boolean offeringStatus;
    private String offeringType;
    private int offeringPrice;

    public Offering() {
        this.customerName = "noname";
        this.offeringDescription = "something";
        this.offeringName =  "noname";
        this.offeringStatus = false;
        this.offeringMaxCount = 0;
        this.offeringType = "Something";
        this.offeringPrice = 0;
    }

    public Offering(String customerName, String offeringName, String offeringDescription, int offeringMaxCount, boolean offeringStatus, String offeringType, int offeringPrice) {
        this.customerName = customerName;
        this.offeringName = offeringName;
        this.offeringDescription = offeringDescription;
        this.offeringMaxCount = offeringMaxCount;
        this.offeringStatus = offeringStatus;
        this.offeringType = offeringType;
        this.offeringPrice = offeringPrice;
    }

    public Offering(Offering off) {
        this.customerName = off.getCustomerName();
        this.offeringDescription = off.getOfferingDescription();
        this.offeringName =  off.getOfferingName();
        this.offeringStatus = off.isOfferingStatus();
        this.offeringMaxCount = off.getOfferingMaxCount();
        this.offeringType = off.getOfferingType();
        this.offeringPrice = off.getOfferingPrice();
    }

    public int getOfferingPrice() {
        return offeringPrice;
    }

    public void setOfferingPrice(int offeringPrice) {
        this.offeringPrice = offeringPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customer_name) {
        this.customerName = customer_name;
    }

    public String getOfferingName() {
        return offeringName;
    }

    public void setOfferingName(String offering_name) {
        this.offeringName = offering_name;
    }

    public String getOfferingDescription() {
        return offeringDescription;
    }

    public void setOfferingDescription(String offering_description) {
        this.offeringDescription = offering_description;
    }

    public int getOfferingMaxCount() {
        return offeringMaxCount;
    }

    public void setOfferingMaxCount(int offering_max_count) {
        this.offeringMaxCount = offering_max_count;
    }

    public boolean isOfferingStatus() {
        return offeringStatus;
    }

    public void setOfferingStatus(boolean offering_status) {
        this.offeringStatus = offering_status;
    }

    public String getOfferingType() {
        return offeringType;
    }

    public void setOfferingType(String offeringType) {
        this.offeringType = offeringType;
    }


}
