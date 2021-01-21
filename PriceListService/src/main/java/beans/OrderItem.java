package beans;

public class OrderItem {
    private String user_id;
    private Integer res_id;
    private Integer res_count;



    public OrderItem(String user_id, Integer res_id, Integer res_count) {
        this.user_id = user_id;
        this.res_id = res_id;
        this.res_count = res_count;
    }

    public OrderItem() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getRes_id() {
        return res_id;
    }

    public void setRes_id(Integer res_id) {
        this.res_id = res_id;
    }

    public Integer getRes_count() {
        return res_count;
    }

    public void setRes_count(Integer res_count) {
        this.res_count = res_count;
    }
}
