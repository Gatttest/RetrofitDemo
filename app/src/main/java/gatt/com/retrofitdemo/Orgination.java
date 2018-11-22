package gatt.com.retrofitdemo;

/**
 * Created by Sushil Chaurasiya on 03/11/2018.
 */

public class Orgination {
    String id;
    String org_name;

    public Orgination(String id, String org_name) {
        this.id = id;
        this.org_name = org_name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String org_name) {
        this.org_name = org_name;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return org_name;
    }
}
