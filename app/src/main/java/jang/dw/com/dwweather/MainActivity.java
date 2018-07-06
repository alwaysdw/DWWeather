package jang.dw.com.dwweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import Retorfit.RetrofitService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textview;

    private static final String TAG = "DWWeather";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate()");

        editText = (EditText) findViewById(R.id.editText);
        textview = (TextView) findViewById(R.id.textView);

        //2018.07.03
        Init();
        //2018.07.03 edit from github

        //2018.07.03 edit from bitbucket

        //2018.07.04 add retrofit
        /*Retrofit client = new Retrofit.Builder().baseUrl("http://api.openweathermap.org").addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitService service = client.create(RetrofitService.class);*/

    }

    public void Init(){
        Log.d(TAG, "Init()");

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
