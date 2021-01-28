package beans;

import java.util.List;

public class Order {
    private String buyer;
    private String date;
    private int totalPrice;
    private List<BasketItem> items;

    public Order() {
    }

    public Order(String buyer, String date, int totalPrice, List<BasketItem> items) {
        this.buyer = buyer;
        this.date = date;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }
}
