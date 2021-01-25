package beans;

public class Filter {
    private String customerName;
    private String offeringType;

    public Filter() {
        this.customerName = "Noname";
        this.offeringType = "Something";
    }

    public Filter(String customerName, String resourceName) {
        this.customerName = customerName;
        this.offeringType = resourceName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOfferingType() {
        return offeringType;
    }

    public void setOfferingType(String resourceName) {
        this.offeringType = resourceName;
    }
}
