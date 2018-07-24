package jang.dw.com.dwweather.api;

import jang.dw.com.dwweather.data.CurrentWeatherInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {

    @GET("weatehr?mode=json")
    Call<CurrentWeatherInfo> getCurrentWeather(@Query("apikey")String apikey, @Query("q") String city);

    @GET("weather?mode=json")
    Call<CurrentWeatherInfo> getCurrentWeather(@Query("apikey")String apikey, @Query("id")int city);

    @GET("weather?mode=json")
    Call<CurrentWeatherInfo> getCurrentWeather(@Query("apikey")String apikey, @Query("lat")double latitue, @Query("lon") double longitude);

    /*@GET("forecast?mode=json")
    Call<ForecastWeatherInfo> getForecastWeather(@Query("apiKey") String apiKey, @Query("q") String city);

    @GET("forecast?mode=json")
    Call<ForecastWeatherInfo> getForecastWeather(@Query("apiKey") String apiKey, @Query("id") int city);

    @GET("forecast?mode=json")
    Call<ForecastWeatherInfo> getForecastWeather(@Query("apiKey") String apiKey, @Query("lat") double latitude, @Query("lon") double longitude);*/
}
