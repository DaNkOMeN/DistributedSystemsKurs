package service;

import beans.BasketItem;
import beans.Order;
import beans.TotalcostCustomer;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class EventsService {


    @Transactional
    public Response addToHistory(List<BasketItem> basketItems) {
        String kek = "";
        for (BasketItem basketItem: basketItems) {
            basketItem.id = null;
            basketItem.setOrderDate(LocalDate.now().toString());
            kek += basketItem.getOrderDate();
            basketItem.persist();
        }
        return Response.ok(kek).build();
    }


    @Transactional
    public List<TotalcostCustomer> filterBasketItemsByYearAndMonth(String yearAndMonthFilter){
        List<BasketItem> filterByYearAndCount = BasketItem.listAll().stream().map(item -> (BasketItem)item).filter(item -> item.getOrderDate() != null && item.getOrderDate().substring(0,7).equals(yearAndMonthFilter)).collect(Collectors.toList());
        Map<String, Integer> buyerCostMap = new HashMap<>();
        for (BasketItem item : filterByYearAndCount) {
            if (buyerCostMap.containsKey(item.getCustomerName())) {
                int q = buyerCostMap.get(item.getCustomerName()) + item.getOfferingPrice() * item.getCount();
                buyerCostMap.put(item.getCustomerName(), q);
            } else {
                buyerCostMap.put(item.getCustomerName(), item.getOfferingPrice() * item.getCount());
            }
        }
        List<TotalcostCustomer> resultList = new ArrayList<>();

        for (Map.Entry entry : buyerCostMap.entrySet()) {
            resultList.add(new TotalcostCustomer((String) entry.getKey(),(Integer) entry.getValue()));
        }
        return resultList;
    }

    public List<Order> getFilteredOrder(String yearAndMonthFilter) {
        List<BasketItem> filterByYearAndCount = BasketItem.listAll().stream().map(item -> (BasketItem)item).filter(item -> item.getOrderDate() != null && item.getOrderDate().substring(0,7).equals(yearAndMonthFilter)).collect(Collectors.toList());
        Map<String, List<BasketItem>> buyersOrders = new HashMap<>();
        for (BasketItem item : filterByYearAndCount) {
            String buyerName = item.getBuyer();
            if (buyersOrders.containsKey(buyerName)) {
                List<BasketItem> buyersList = buyersOrders.get(buyerName);
                buyersList.add(item);
                buyersOrders.put(buyerName, buyersList);
            } else {
                List<BasketItem> addableList = new ArrayList<>();
                addableList.add(item);
                buyersOrders.put(buyerName, addableList);
            }
        }
        List<Order> resultList = new ArrayList<>();
        for (Map.Entry entry: buyersOrders.entrySet()) {
            Order order = new Order();
            order.setBuyer((String) entry.getKey());
            List<BasketItem> basketItems = (List<BasketItem>) entry.getValue();
            order.setItems(basketItems);
            order.setDate(basketItems.get(0).getOrderDate());
            int sum = 0;
            for (BasketItem basketItem: basketItems) {
                sum += basketItem.getCount();
            }
            order.setTotalPrice(sum);
            resultList.add(order);
        }
        return resultList;
    }
}
