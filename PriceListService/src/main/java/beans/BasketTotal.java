package beans;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BasketTotal {


    private int _itemCount;
    private int _totalCost;


    public BasketTotal() {
        this._itemCount = 0;
        this._totalCost = 0;
    }

    public int get_itemCount() {
        return _itemCount;
    }

    public void set_itemCount(int _itemCount) {
        this._itemCount = _itemCount;
    }

    public int get_totalCost() {
        return _totalCost;
    }

    public void set_totalCost(int _totalCost) {
        this._totalCost = _totalCost;
    }

    public BasketTotal(String buyerName){
        List<BasketItem> basketItems = BasketItem.listAll().stream().map(item -> (BasketItem)item).filter(item -> Objects.equals(item.getBuyer(), buyerName)).collect(Collectors.toList());
        int itemCount = 0;
        int totalSum = 0;
        for (BasketItem item: basketItems) {
            itemCount += item.getCount();
            totalSum += item.getOfferingPrice() * item.getCount();
        }
        this._itemCount = itemCount;
        this._totalCost = totalSum;

    }

    public BasketTotal(int _itemCount, int _totalCost) {
        this._itemCount = _itemCount;
        this._totalCost = _totalCost;
    }
}
