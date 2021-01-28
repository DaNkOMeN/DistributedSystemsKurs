package beans;

public class TotalcostCustomer {

    private String customerName;
    private int totalCost;

    public TotalcostCustomer() {
    }

    public TotalcostCustomer(String customerName, int totalCost) {
        this.customerName = customerName;
        this.totalCost = totalCost;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
