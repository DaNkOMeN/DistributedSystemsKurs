package beans;

public class OfferingRequest {

    private String customer; //chi offeringi
    private int offeringCount; // kolvo-na stranitsu
    private int pageNumber; // dlya kakoy stranitsy


    public OfferingRequest(String customer, int offeringCount, int pageNumber) {
        this.customer = customer;
        this.offeringCount = offeringCount;
        this.pageNumber = pageNumber;
    }

    public OfferingRequest() {
        this.customer = "default";
        this.offeringCount = 0;
        this.pageNumber = 0;
    }

    public int getOfferingCount() {
        return offeringCount;
    }

    public void setOfferingCount(int offeringCount) {
        this.offeringCount = offeringCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
