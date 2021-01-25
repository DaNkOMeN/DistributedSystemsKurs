package service;

import beans.*;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CatalogService {

    public CatalogService(){

    }

    @Transactional
    public ResponseItem addPriceListItem(PriceListItem item){
        item.persist();
        return new ResponseItem(true," Success");
    }

    @Transactional
    public ResponseItem confirmOrder(List<OrderItem> orderItems){
        List<PriceListItem> priceListItems = getPriceList();
        //проверяем, достаточное ли количество ресурсов из тех, что заказано
        boolean contains = true;
        //идем по всем ресурсам из запроса
        for (OrderItem orderItem: orderItems) {
            //ищем ресурс с айдишником из запроса
           PriceListItem findedItem = PriceListItem.findById((Long)orderItem.getRes_id().longValue());
           if (findedItem != null) {
               //если мы такой ресурс нашли, то проверяем количество доступных на данный момент ресурсов
                if (orderItem.getRes_count() > findedItem.getResMaxCount()) {
                    //если попросили больше чем есть (мало ли), тогда говорим что низя
                    contains = false;
                }
           } else {
               //а если вообще ресурса с таким id не нашло, то абдыщ
               throw new RuntimeException("Несуществующий ресурс");
           }
        }
        //если количество запрашиваемых элементов збс
        if (contains) {
            //то проходим по всем элементам в бд и уменьшаем их кол-во
            for (OrderItem orderItem: orderItems) {
                PriceListItem findedItem = PriceListItem.findById((Long)orderItem.getRes_id().longValue());
                findedItem.setResMaxCount( findedItem.getResMaxCount() - orderItem.getRes_count());
                findedItem.persist();
            }
            //возвращаем что збс
            return new ResponseItem(true, "Success");
        } else {
            //если не содержит то груст
            return new ResponseItem(false, "Not success");
        }
    }


    @Transactional
    public List<PriceListItem> getPriceList() {
        return PriceListItem.listAll();
    }

    public File getPriceListAsFile(){
        File returnedFile = new File("result.txt");
        try (FileWriter writer = new FileWriter(returnedFile, false)){
            writer.write("Список всех доступных ресурсов");
            writer.append('\n');
            writer.write("------------------------------------------------------------------------------------------");
            writer.append('\n');
            for (PriceListItem listItem: getPriceList()){
                writer.write("Наименование ресурса: " + listItem.getResName());
                writer.append('\n');
                writer.write("Цена ресурса: " + listItem.getResPrice());
                writer.append('\n');
                writer.write("Максимальное количество ресурса: " + listItem.getResMaxCount());
                writer.append('\n');
                writer.write("Тип ресурса " + listItem.getResType().name());
                writer.append('\n');
                writer.write("Описание ресурса: ");
                writer.append('\n');
                writer.write(listItem.getResDescription());
            }
            writer.flush();

        }catch (IOException ex){
            ex.printStackTrace();
        }
        return returnedFile;
    }

    @Transactional
    public Response extraChargeItems(List<ExtraChargeItem> items) {
        List<PriceListItem> priceListItems =  PriceListItem.listAll().stream().map(it -> (PriceListItem)it).collect(Collectors.toList());
        for (PriceListItem priceListItem : priceListItems) {
            for (ExtraChargeItem item : items) {
               if (priceListItem.getResType().name().equals(item.getRes_type_name())){
                   priceListItem.setResExtraCharge(item.getRes_extra_charge());
               }
            }
        }
        return Response.ok().build();
    }

    @Transactional
    public Response addInBasket(BasketPriceListItem basketItem) {
        basketItem.persist();
        return Response.ok().build();
    }

    @Transactional
    public Response deleteInBasket(BasketPriceListItem basketItem) {
        basketItem.delete();
        return Response.ok().build();
    }

    public Response updateInBasket(BasketPriceListItem basketItem) {

        List<BasketPriceListItem> basketPriceListItems = BasketPriceListItem.listAll().stream().map(item -> (BasketPriceListItem)item).collect(Collectors.toList());
        for (BasketPriceListItem item: basketPriceListItems){
            if      (item.getResName().equals(basketItem.getResName()) &&
                    item.getResDescription().equals(basketItem.getResDescription()) &&
                    item.getResPrice() == basketItem.getResPrice() &&
                    item.getResMaxCount().equals(basketItem.getResMaxCount()) ) {
                item.setCurrentCount(basketItem.getCurrentCount());
                item.persist();
                return Response.ok().build();
            }
        }
        return Response.notModified("Cant find basketitem").build();
    }

}
