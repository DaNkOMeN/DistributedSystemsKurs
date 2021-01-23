package beans;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Offering extends PanacheEntity {

    private String customer_name;
    private String offering_name;
    private String offering_description;
    private int offering_max_count;
    private boolean offering_status;

    public Offering() {
        this.customer_name = "noname";
        this.offering_description = "something";
        this.offering_name =  "noname";
        this.offering_status = false;
        this.offering_max_count = 0;
    }

    public Offering(String customer_name, String offering_name, String offering_description, int offering_max_count, boolean offering_status) {
        this.customer_name = customer_name;
        this.offering_name = offering_name;
        this.offering_description = offering_description;
        this.offering_max_count = offering_max_count;
        this.offering_status = offering_status;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getOffering_name() {
        return offering_name;
    }

    public void setOffering_name(String offering_name) {
        this.offering_name = offering_name;
    }

    public String getOffering_description() {
        return offering_description;
    }

    public void setOffering_description(String offering_description) {
        this.offering_description = offering_description;
    }

    public int getOffering_max_count() {
        return offering_max_count;
    }

    public void setOffering_max_count(int offering_max_count) {
        this.offering_max_count = offering_max_count;
    }

    public boolean isOffering_status() {
        return offering_status;
    }

    public void setOffering_status(boolean offering_status) {
        this.offering_status = offering_status;
    }
}
