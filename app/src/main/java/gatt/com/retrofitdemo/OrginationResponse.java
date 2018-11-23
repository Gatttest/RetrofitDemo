package gatt.com.retrofitdemo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sushil Chaurasiya on 03/11/2018.
 */


    class Menu{
    BlockResponse response;

        public BlockResponse getresponse() {
            return response;
        }


    public class BlockResponse {
        String code;
        private List<BlockList> details = new ArrayList<BlockList>();

        public String getCode() {
            return code;
        }

        public List<BlockList> getBlockResponseList() {
            return details;
        }

    }

    class BlockList{
        String id;
        String name;
        String online_status;

        public String getId() {
            return id;
        }

        public String get_name() {
            return name;
        }


        public String getStatus() {
            return online_status;
        }

    }


}
