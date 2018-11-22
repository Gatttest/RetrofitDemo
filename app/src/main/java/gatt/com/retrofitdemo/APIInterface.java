package gatt.com.retrofitdemo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Sushil Chaurasiya on 01/11/2018.
 */
public interface APIInterface {

    String BASE_URL = "http://www.swingit.co.in/api/";
    @GET ("getOrgList.php")
    Call<OrgList> getOrgList();
   /* Call<ResponseBody> getOrgList();*/
  // @FormUrlEncoded
    @GET("getFriendsList.php")
    Call<ResponseBody> getBlockList(@Query("userid") String userid);
   //Call<ResponseBody> getBlockList(@Path("userid") String id);
   // Path
    @POST("updateUserChannel.php")
    Call<ResponseBody> postChannel(@Query("uid") String userid, @Query("org_name") String org_name,@Query("ins_name") String ins_name );

}
