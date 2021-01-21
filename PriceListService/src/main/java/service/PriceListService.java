package service;

import beans.OrderItem;
import beans.PriceListItem;
import beans.ResourceType;
import beans.ResponseItem;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PriceListService {

    public PriceListService(){

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
                if (orderItem.getRes_count() > findedItem.getRes_max_count()) {
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
                findedItem.setRes_max_count( findedItem.getRes_max_count() - orderItem.getRes_count());
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
                writer.write("Наименование ресурса: " + listItem.getRes_name());
                writer.append('\n');
                writer.write("Цена ресурса: " + listItem.getRes_price());
                writer.append('\n');
                writer.write("Максимальное количество ресурса: " + listItem.getRes_max_count());
                writer.append('\n');
                writer.write("Тип ресурса " + listItem.getRes_type().name());
                writer.append('\n');
                writer.write("Описание ресурса: ");
                writer.append('\n');
                writer.write(listItem.getRes_description());
            }
            writer.flush();

        }catch (IOException ex){
            ex.printStackTrace();
        }
        return returnedFile;
    }
}
