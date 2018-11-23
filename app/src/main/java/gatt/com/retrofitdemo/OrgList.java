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
    OrgResponse response;

    public OrgResponse getresponse() {
        return response;
    }
    public class OrgResponse {
        String code;
        private List<OrgListt> orglist = new ArrayList<OrgListt>();
        public String getCode() {
            return code;
        }
        public List<OrgListt> getOrgListResponse() {
            return orglist;
        }
    }
    class OrgListt{
        String id;
        String org_name;
        public String getId() {
            return id;
        }
        public String get_orgname() {
            return org_name;
        }
    }
}