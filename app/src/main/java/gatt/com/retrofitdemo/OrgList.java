package gatt.com.retrofitdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sushil Chaurasiya on 01/11/2018.
 */

public class OrgList {

    @Expose
    @SerializedName("orglist")
    private ArrayList<Orgination> orgs;


    @Expose
    @SerializedName("response")
    private JSONObject code;

    public OrgList(ArrayList<Orgination> orgs, JSONObject code) {
        this.orgs = orgs;
        this.code = code;
    }
    public JSONObject getCode() {
        return code;
    }
    public void setCode(JSONObject code) {
        this.code = code;
    }
    public ArrayList<Orgination> getOrgsList() {
        return orgs;
    }
    public void setOrgsList(ArrayList<Orgination> orgs) {
        this.orgs = orgs;
    }
}
