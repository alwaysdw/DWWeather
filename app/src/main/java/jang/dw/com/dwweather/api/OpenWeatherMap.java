package jang.dw.com.dwweather.api;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.time.ZonedDateTime;

import jang.dw.com.dwweather.data.CurrentWeatherInfo;
import jang.dw.com.dwweather.data.CurrentWeatherInfoDeserializer;
import jang.dw.com.dwweather.data.ForecastWeatherInfo;
import jang.dw.com.dwweather.data.Temperature;
import jang.dw.com.dwweather.data.TemperatureDeserializer;
import jang.dw.com.dwweather.data.ZonedDateTimeDeserializer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class OpenWeatherMap {

    private static final String DEFAULT_VERSION = "2.5";

    private final OpenWeatherMapService  openWeatherMapService;
    private final String apikey;


    public OpenWeatherMap(String apikey){
        this(apikey, DEFAULT_VERSION);
    }

    public OpenWeatherMap(String apikey, String apiVersion){
        this.apikey = "a4281c5ced646a1e0cfc86f9b33c50b8";

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        module.addDeserializer(Temperature.class, new TemperatureDeserializer());
        module.addDeserializer(CurrentWeatherInfo.class, new CurrentWeatherInfoDeserializer(mapper));
        module.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());

        mapper.registerModule(module);

        openWeatherMapService = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/"+ apiVersion + "/")
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build()
                .create(OpenWeatherMapService.class);
    }

    @Nullable
    public CurrentWeatherInfo getCurrentWeather(String city) {
        try {
            Call<CurrentWeatherInfo> call = openWeatherMapService.getCurrentWeather(apikey, city);
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getCurrentWeather(String city, SuccessCallback<CurrentWeatherInfo> callback){
        getCurrentWeather(city, callback,  null);
    }

    /*public volid getCurrentWeather(String city, final sucessCallback<ForecastWeatherInfo>callback){
        get
    }*/

    public void getCurrentWeather(String city, final SuccessCallback<CurrentWeatherInfo> successCallback, final FailCallback failCallback){

    }

    public interface SuccessCallback<T> {

        /**
         * Callback method for successful service calls.
         *
         * @param result The result received from the service.
         */
        void onSuccess(T result);

    }

    public interface FailCallback {

        /**
         * Callback method for failed service calls.
         *
         * @param e The throwable that occurred during service communication.
         */
        void onFail(Throwable e);

    }

}
