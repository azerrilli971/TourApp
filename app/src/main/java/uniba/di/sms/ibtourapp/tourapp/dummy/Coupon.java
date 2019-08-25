package uniba.di.sms.ibtourapp.tourapp.dummy;

public class Coupon {
    public Coupon(){}

    private String id;
    private String key;

    public Coupon(String id, String key) {
        this.id = id;
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String data) {
        this.key = data;
    }
}
