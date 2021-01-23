package beans;

public class ExtraChargeItem {
    private String res_type_name;
    private int res_extra_charge;


    public ExtraChargeItem() {

    }

    public ExtraChargeItem(String res_type_name, int res_extra_charge) {
        this.res_type_name = res_type_name;
        this.res_extra_charge = res_extra_charge;
    }

    public String getRes_type_name() {
        return res_type_name;
    }

    public void setRes_type_name(String res_type_name) {
        this.res_type_name = res_type_name;
    }

    public int getRes_extra_charge() {
        return res_extra_charge;
    }

    public void setRes_extra_charge(int res_extra_charge) {
        this.res_extra_charge = res_extra_charge;
    }
}
