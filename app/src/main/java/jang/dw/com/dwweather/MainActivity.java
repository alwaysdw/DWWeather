package jang.dw.com.dwweather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import Retorfit.GetWeatherApi;
import Retorfit.WeatherDataBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "DWWeather";

    private static final String API_URL= "http://api.openweathermap.org";

    private double lat = 37.540705;   // seoul 위도

    private double lon = 126.956764;  // seoul 경도

    //private static final String CITY_NAME ="London";
    //private static final int CITY_ID = 2519240; // 2519240  1273874

    //private static final String UNITS ="metric";
    private static final String APP_ID ="a4281c5ced646a1e0cfc86f9b33c50b8";  // 전달 받은 app id value

    private Button Weathersync,WeatherSsync;
    private TextView temperature, pressure, humidity,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_main);
        Log.d(TAG, "onCreate()");

        //2018.07.03
        Init();

        //2018.07.03 edit from github

        //2018.07.03 edit from bitbucket

        //2018.07.04 add retrofit
        /*Retrofit client = new Retrofit.Builder().baseUrl("http://api.openweathermap.org").addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitService service = client.create(RetrofitService.class);*/

        /*ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        module.addDeserializer(Temperature.class, new TemperatureDeserializer());
        module.addDeserializer(CurrentWeatherInfo.class, new CurrentWeatherInfoDeserializer(mapper));
        module.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());

        mapper.registerModule(module);

        openWeatherMapService = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5")
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build()
                .create(OpenWeatherMapService.class);*/

    }

    public void Init(){
        Log.d(TAG, "Init()");
        Weathersync = (Button) findViewById(R.id.WeatherSync);
        WeatherSsync = (Button) findViewById(R.id.WeatherAsync);
        temperature =(TextView) findViewById(R.id.temperature);
        pressure =(TextView) findViewById(R.id.pressure);
        humidity =(TextView) findViewById(R.id.humidity);
        result = (TextView) findViewById(R.id.result);
        Weathersync.setOnClickListener(this);
        WeatherSsync.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick()");
        switch (v.getId()){

            case R.id.WeatherSync :
                getWeatherSync();
                break;

            case R.id.WeatherAsync :
                getWeatherAsync();
                break;
        }

    }

    private void getWeatherSync(){
        Log.d(TAG, "getWeatherSync()");
        GetWeatherSync getWeatherSync = new GetWeatherSync();
        getWeatherSync.execute();

    }

    private class GetWeatherSync extends AsyncTask<Void,Void,WeatherDataBean>{

        Retrofit retrofit;
        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute()");
            super.onPreExecute();
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        @Override
        protected void onPostExecute(WeatherDataBean weatherDataBean) {
            super.onPostExecute(weatherDataBean);
        }

        @Override
        protected WeatherDataBean doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground()");
            WeatherDataBean weatherAPIResult = null;

            try {
                GetWeatherApi getWeatherApi = retrofit.create(GetWeatherApi.class);
                Call<WeatherDataBean> call = getWeatherApi.getWeatherFromApiSyncAndAsync("a4281c5ced646a1e0cfc86f9b33c50b8",lat,lon);
                //Call<WeatherAPIResult> call = getWeatherApi.getWeather(CITY_ID, APP_ID);
                Response<WeatherDataBean> response = call.execute();
                weatherAPIResult = response.body();
            } catch (IOException e) {
                e.printStackTrace();

            }

            return weatherAPIResult;
        }
    }


    public void getWeatherAsync(){
        Log.d(TAG, "getWeatherAsync()");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetWeatherApi getWeatherApi = retrofit.create(GetWeatherApi.class);

        //비동기식 방식
        Call<WeatherDataBean> call = getWeatherApi.getWeatherFromApiSyncAndAsync("a4281c5ced646a1e0cfc86f9b33c50b8",lat,lon);
        //Call<WeatherAPIResult> call = getWeatherApi.getWeather(CITY_ID, APP_ID);

        call.enqueue(new Callback<WeatherDataBean>() {

            @Override
            public void onResponse(Call<WeatherDataBean> call, Response<WeatherDataBean> response) {
                Log.d(TAG, "onResponse()");

                WeatherDataBean Data = response.body();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                /*Gson gson = new GsonBuilder().setPrettyPrinting().create();
                result.setText(gson.toJson(Data));*/

                Log.d(TAG, "Data toString= "+gson.toJson(Data));
                updateUI(response.body());

            }

            @Override
            public void onFailure(Call<WeatherDataBean> call, Throwable t) {
                Log.d(TAG, "onFailure()");
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void updateUI(WeatherDataBean weatherDataBean){
        Log.d(TAG, "updateUI()");

        Log.d(TAG, "updateUI getCity = "+weatherDataBean.getMain().getCity());
        Log.d(TAG, "updateUI getCountry = "+weatherDataBean.getMain().getCountry());
        Log.d(TAG, "updateUI getTemp = "+weatherDataBean.getMain().getTemp());

        temperature.setText("Temperature : "+ weatherDataBean.getMain().getTemp() + " [°C]");
        pressure.setText("Pressure : "+ weatherDataBean.getMain().getPressure());
        humidity.setText("Humidity : " + weatherDataBean.getMain().getHumidity());
        result.setText("City : "+ weatherDataBean.getMain().getCity());

        /*temperature.setText("Temperature : " + weatherDataBean.getMain().getTemp() + " Celsius");
        pressure.setText("Pressure : " + weatherDataBean.getMain().getPressure());
        humidity.setText("Humidity : " + weatherDataBean.getMain().getHumidity());*/

        /*temperature.setText("Temperature : " + weatherAPIResult.getTemp() + " Celsius");
        pressure.setText("Pressure : " + weatherAPIResult.getPressure());
        humidity.setText("Humidity : " + weatherAPIResult.getHumidity());*/
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
