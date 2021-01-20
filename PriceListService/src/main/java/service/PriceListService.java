package service;

import beans.PriceListItem;
import beans.ResourceType;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PriceListService {

    List<PriceListItem> itemsList = new ArrayList<>();

    public PriceListService(){
        PriceListItem first = new PriceListItem(1, "Jija", 10, 50, ResourceType.SPACE, "Lol");
        PriceListItem second = new PriceListItem(1, "Prejija", 20, 60, ResourceType.COSTUME, "kek");
        PriceListItem thirth = new PriceListItem(1, "Perjija", 30, 70, ResourceType.DECORATION, "Chebureck");
        itemsList.add(first);
        itemsList.add(second);
        itemsList.add(thirth);
    }

    @Transactional
    public List<PriceListItem> getPriceListItemList(){
        return itemsList;
    }

    @Transactional
    public List<PriceListItem> addPriceListItemList(PriceListItem newItem){
        itemsList.add(newItem);
        return itemsList;
    }
}
