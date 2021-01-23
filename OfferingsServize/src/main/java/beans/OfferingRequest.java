package beans;

public class OfferingRequest {



//    private JsonWebToken token;
//    private Offering offering;
//
//    public OfferingRequest(JsonWebToken token, Offering offering) {
//        this.token = token;
//        this.offering = offering;
//    }
//
//    public OfferingRequest() {
//        this.token = null;
//        this.offering = new Offering();
//    }
//
//    public JsonWebToken getToken() {
//        return token;
//    }
//
//    public void setToken(JsonWebToken token) {
//        this.token = token;
//    }
//
//    public Offering getOffering() {
//        return offering;
//    }
//
//    public void setOffering(Offering offering) {
//        this.offering = offering;
//    }
//
    private String token;
    private Offering offering;

    public OfferingRequest(String token, Offering offering) {
        this.token = token;
        this.offering = offering;
    }

    public OfferingRequest() {
        this.token = null;
        this.offering = new Offering();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Offering getOffering() {
        return offering;
    }

    public void setOffering(Offering offering) {
        this.offering = offering;
    }
}
