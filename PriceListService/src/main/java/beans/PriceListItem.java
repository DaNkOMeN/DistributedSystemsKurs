package beans;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

//Элемент прайслист
@Entity
@Cacheable
public class PriceListItem extends PanacheEntity {

    private int res_id;
    private String res_name;
    private int res_price;
    private int res_max_count;
    private ResourceType res_type;
    private String res_description;

    public PriceListItem(int res_id, String res_name, int res_price, int res_max_count,
                         ResourceType res_type, String res_description) {
        this.res_id = res_id;
        this.res_name = res_name;
        this.res_price = res_price;
        this.res_max_count = res_max_count;
        this.res_type = res_type;
        this.res_description = res_description;
    }

    public PriceListItem() {
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public int getRes_price() {
        return res_price;
    }

    public void setRes_price(int res_price) {
        this.res_price = res_price;
    }

    public int getRes_max_count() {
        return res_max_count;
    }

    public void setRes_max_count(int res_max_count) {
        this.res_max_count = res_max_count;
    }

    public ResourceType getRes_type() {
        return res_type;
    }

    public void setRes_type(ResourceType res_type) {
        this.res_type = res_type;
    }

    public String getRes_description() {
        return res_description;
    }

    public void setRes_description(String res_description) {
        this.res_description = res_description;
    }
}
