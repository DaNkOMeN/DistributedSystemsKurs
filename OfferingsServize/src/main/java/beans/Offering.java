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

    public Offering() {
        this.customerName = "noname";
        this.offeringDescription = "something";
        this.offeringName =  "noname";
        this.offeringStatus = false;
        this.offeringMaxCount = 0;
        this.offeringType = "Something";
    }

    public Offering(String customerName, String offeringName, String offeringDescription, int offeringMaxCount, boolean offeringStatus, String offeringType) {
        this.customerName = customerName;
        this.offeringName = offeringName;
        this.offeringDescription = offeringDescription;
        this.offeringMaxCount = offeringMaxCount;
        this.offeringStatus = offeringStatus;
        this.offeringType = offeringType;
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
