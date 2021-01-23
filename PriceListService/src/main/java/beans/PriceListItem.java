package beans;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

//Элемент прайслист
@Entity
@Cacheable
public class PriceListItem extends PanacheEntity {

    private String resName; //назавание ресурса
    private int resPrice;  //цена ресурса
    private Integer resMaxCount; //максимальное количество ресурса
    private ResourceType resType; //тип ресурса
    private String resDescription; //описание ресурса
    private int resExtraCharge; //наценка

    public PriceListItem(String resName, int resPrice, int resMaxCount,
                         ResourceType resType, String resDescription, int resExtraCharge) {
        this.resName = resName;
        this.resPrice = resPrice;
        this.resMaxCount = resMaxCount;
        this.resType = resType;
        this.resDescription = resDescription;
        this.resExtraCharge = 1;
    }

    public PriceListItem() {
    }


    public int getResExtraCharge() {
        return resExtraCharge;
    }

    public void setResExtraCharge(int res_extra_charge) {
        this.resExtraCharge = res_extra_charge;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public int getResPrice() {
        return resPrice;
    }

    public void setResPrice(int res_price) {
        this.resPrice = res_price;
    }

    public Integer getResMaxCount() {
        return resMaxCount;
    }

    public void setResMaxCount(Integer res_max_count) {
        this.resMaxCount = res_max_count;
    }

    public ResourceType getResType() {
        return resType;
    }

    public void setResType(ResourceType res_type) {
        this.resType = res_type;
    }

    public String getResDescription() {
        return resDescription;
    }

    public void setResDescription(String res_description) {
        this.resDescription = res_description;
    }
}
