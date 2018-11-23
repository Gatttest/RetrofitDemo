package gatt.com.retrofitdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sushil Chaurasiya on 01/11/2018.
 */

public class myProfileResponse {
    Response response;
    public Response getResponse() {
        return response;
    }

class Response{

    String code;
    String noofonlineusers;
    List<profileList> details = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public String getOnline_user() {
        return noofonlineusers;
    }

    public List<profileList> getProfileLists() {
        return details;
    }
}

class profileList{

    String id;
    String name;
    String location;
    String age;
    String getrequest;
    List<String> img = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getAge() {
        return age;
    }

    public String getGetrequest() {
        return getrequest;
    }

    public List<String> getImg() {
        return img;
    }




}


}