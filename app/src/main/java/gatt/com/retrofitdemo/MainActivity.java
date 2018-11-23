package gatt.com.retrofitdemo;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
   @BindView(R.id.strt_button) Button startbtn;
   @BindViews({R.id.orgname,R.id.institutename})
   List<Spinner> channel;
   @BindViews({R.id.org_edittext, R.id.institute_edittext})
   List<EditText> channel_ed;
  // String TAG="ChooseProfileActivityTag";
   ArrayList<String> org_list= new ArrayList<>();
   ArrayList<String> ins_list= new ArrayList<>();
   APIInterface api;
   String TAG= "MAINACTIVITY_TAG";
   List<Menu.BlockList> blockLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        org_list.add("Select organization");
        ins_list.add("Select institute");
        ButterKnife.bind(this);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();
        //creating the api interface
         api = retrofit.create(APIInterface.class);

        // getRequest();                  // get Request
        // getRequestParameter();       // get request with parameter
       // postRequestParameter();     // post request with parameter
         getMyProfileData();    // get request with parameter
    }

    @OnClick(R.id.strt_button)
    public void submit() {

            Toast.makeText(MainActivity.this, "Hello Butterknife annotation", Toast.LENGTH_SHORT).show();
            startbtn.setText("xxxhhgxdddddguf");
            startbtn.setVisibility(View.VISIBLE);
    }
    public void getRequest()
    {
        //now making the call object
        //Here we are using the api method that we created inside the api interface
        Call<OrgList> call = api.getOrgList();
        //then finallly we are making the call using enqueue()
        //and callback is having two methods onRespnose() and onFailure
        //if the request is successfull we will get the correct response and onResponse will be executed
        call.enqueue(new Callback<OrgList>() {
            @Override
            public void onResponse(Call<OrgList> call, Response<OrgList> response) {
                if(response!=null && response.isSuccessful()) {
                    OrgList.OrgResponse orgs = response.body().getresponse();
                  //now we can do whatever we want with this list

                  List<OrgList.OrgListt> orggg = new ArrayList<>();
                  orggg= orgs.getOrgListResponse();

                  Log.d("responseee 11", "ooooo  " +orgs);
                  Log.d("responseee 11", "oooooyy  " +orggg);
                  Log.d("responseee 11", "" +response.body().getresponse().getCode());
                  Log.d("responseee 22", "" + response.body().getresponse().getOrgListResponse());
                  Log.d("responseee 22", "" + response.body().getresponse().getOrgListResponse().get(0).getId());
                  Log.d("responseee 22", "" + response.body().getresponse().getOrgListResponse().get(0).get_orgname());
                }
                else{
                    //Log.d("responseee 77", "11111111111111" );
                }
            }
            @Override
            public void onFailure(Call<OrgList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                // Log.d("responseee 55",""+ t.getMessage());
            }
        });
    }
    public void getRequestParameter(){
        Call<ResponseBody> blocklist = api.getBlockList("1");
        blocklist.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response!=null && response.isSuccessful()) {
                    //now we can do whatever we want with this list
                    try {
                        String a= response.body().string();
                        Log.d("responseee 1122",  a);

                        Gson gson = new Gson();
                        Log.d("responseee 1_____", "dfdddddddddf " + a);
                        Menu m = gson.fromJson(a, Menu.class);
                        Log.d("responseee 11223333444", "dfdd___ " + m.getresponse().getCode());
                        blockLists =  m.getresponse().getBlockResponseList();
                        Log.d("responseee 11223333444", "dfdddddddddf " + m.getresponse().getBlockResponseList());
                        Log.d("responseee 11223333444", "dfd " +blockLists);

                      for(int i=0; i<blockLists.size();i++)
                        {
                        Log.d("responseee 11223333444", "dfdddddddddf " + blockLists.get(i).getId());
                        Log.d("responseee 11223333444", "dfdddddddddf " + blockLists.get(i).get_name());
                        Log.d("responseee 11223333444", "dfdddddddddf " + blockLists.get(i).getStatus());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else{
                    Log.d("responseee 77", "11111111111111" );
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("responseee 55",""+ t.getMessage());
            }
        });





    }


    public void getMyProfileData(){
        Call<myProfileResponse> myProfile = api.getMyProfileData("1");
        myProfile.enqueue(new Callback<myProfileResponse>() {
            @Override
            public void onResponse(Call<myProfileResponse> call, Response<myProfileResponse> response) {
                if(response!=null && response.isSuccessful()) {
                    //now we can do whatever we want with this list

                    myProfileResponse response1 = response.body();
                    Log.d("responseee myprofile", "11111 "+response1 );

                    myProfileResponse.Response response2 = response1.getResponse();
                    Log.d("responseee myprofile", "11111 "+response2 );
                    String code= response2.getCode();
                    Log.d("responseee myprofile", "11111code "+code);
                    Log.d("responseee myprofile", "11111user "+response2.getOnline_user());

                    List<myProfileResponse.profileList>  profileLists = response2.getProfileLists();

                    Log.d("responseee myprofile", "11111list "+profileLists);
                    Log.d("responseee myprofile", "1111ddd "+profileLists.get(0).getId());
                    Log.d("responseee myprofile", "aaaa "+profileLists.get(0).getAge());
                    Log.d("responseee myprofile", "nnnn "+profileLists.get(0).getName());
                    Log.d("responseee myprofile", "llll "+profileLists.get(0).getLocation());
                    Log.d("responseee myprofile", "iiiiii "+profileLists.get(0).getImg());

                } else{
                    Log.d("responseee myprofile", "11111111111111" );
                }
            }
            @Override
            public void onFailure(Call<myProfileResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("responseee 55",""+ t.getMessage());
            }
        });
    }
    public void postRequestParameter(){
      Call<ResponseBody> updatechannel = api.postChannel("1","aaaa","bbbbb");
        updatechannel.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response!=null && response.isSuccessful()) {
                    //now we can do whatever we want with this list
                    try {
                        Log.d("responseee 11223333", "" + response.body().string());
                        Log.d(TAG,"working code");
                        Log.d("responsess","correct codeeeee");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else{

                     Log.d("responseee 77", "11111111111111" );
                     Log.d("response","not working");
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("responseee 55",""+ t.getMessage());
            }
        });
    }

   }
