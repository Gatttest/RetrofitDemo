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
   String TAG="ChooseProfileActivityTag";
   ArrayList<String> org_list= new ArrayList<>();
   ArrayList<String> ins_list= new ArrayList<>();
   APIInterface api;

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
        //get Request
      //  getRequest();
        getRequestParameter();
      // postRequestParameter();
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
                    OrgList repos = response.body();
                    ArrayList<Orgination> orgs = response.body().getOrgsList();
                    //now we can do whatever we want with this list
                    // Log.d("responseee 11", "" + response.body());
                    // Log.d("responseee 22", "" + response.body().getCode());
                    // Log.d("responseee 33", "" + orgs);
                    // Log.d("responseee 22",""+ orgs.size());
                    // Log.d("responseee 33",""+ orglist.id);
                    // Log.d("responseee 44",""+ orglist.org_name);
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
                        Log.d("responseee 1122", "" + response.body().string());
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
    public void postRequestParameter(){
      Call<ResponseBody> updatechannel = api.postChannel("1","aaaa","bbbbb");
        updatechannel.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response!=null && response.isSuccessful()) {
                    //now we can do whatever we want with this list
                    try {
                        Log.d("responseee 11223333", "" + response.body().string());
                        Log.d("response","working code");
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
