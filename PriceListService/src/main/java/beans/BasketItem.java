package beans;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class BasketItem extends PanacheEntity {
    private String customerName;
    private String offeringName;
    private String offeringDescription;
    private int offeringMaxCount;
    private boolean offeringStatus;
    private String offeringType;
    private int offeringPrice;
    private int count;
    private String buyer;

    public BasketItem() {
    }

    public BasketItem(Offering off, String buyerName) {
        this.customerName = off.getCustomerName();
        this.offeringDescription = off.getOfferingDescription();
        this.offeringName =  off.getOfferingName();
        this.offeringStatus = off.isOfferingStatus();
        this.offeringMaxCount = off.getOfferingMaxCount();
        this.offeringType = off.getOfferingType();
        this.offeringPrice = off.getOfferingPrice();
        this.count = 1;
        this.buyer = buyerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOfferingName() {
        return offeringName;
    }

    public void setOfferingName(String offeringName) {
        this.offeringName = offeringName;
    }

    public String getOfferingDescription() {
        return offeringDescription;
    }

    public void setOfferingDescription(String offeringDescription) {
        this.offeringDescription = offeringDescription;
    }

    public int getOfferingMaxCount() {
        return offeringMaxCount;
    }

    public void setOfferingMaxCount(int offeringMaxCount) {
        this.offeringMaxCount = offeringMaxCount;
    }

    public boolean isOfferingStatus() {
        return offeringStatus;
    }

    public void setOfferingStatus(boolean offeringStatus) {
        this.offeringStatus = offeringStatus;
    }

    public String getOfferingType() {
        return offeringType;
    }

    public void setOfferingType(String offeringType) {
        this.offeringType = offeringType;
    }

    public int getOfferingPrice() {
        return offeringPrice;
    }

    public void setOfferingPrice(int offeringPrice) {
        this.offeringPrice = offeringPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public BasketItem(String customerName, String offeringName, String offeringDescription, int offeringMaxCount, boolean offeringStatus, String offeringType, int offeringPrice, int count, String buyer) {
        this.customerName = customerName;
        this.offeringName = offeringName;
        this.offeringDescription = offeringDescription;
        this.offeringMaxCount = offeringMaxCount;
        this.offeringStatus = offeringStatus;
        this.offeringType = offeringType;
        this.offeringPrice = offeringPrice;
        this.count = count;
        this.buyer = buyer;
    }
}
