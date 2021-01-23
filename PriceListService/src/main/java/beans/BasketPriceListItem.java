package beans;

public class BasketPriceListItem extends PriceListItem {

    private Integer currentCount;

    public BasketPriceListItem(String res_name, int res_price, int res_max_count, ResourceType res_type, String res_description, int res_extra_charge, Integer currentCount) {
        super(res_name, res_price, res_max_count, res_type, res_description, res_extra_charge);
        this.currentCount = currentCount;
    }

    public BasketPriceListItem() {

    }

    public Integer getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
    }
}
