package Retorfit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetWeatherApi {

    /*@GET("/data/2.5/weather")
    Call<WeatherDataBean> getWeatherFromApiSyncAndAsync(
            @Query("q") String cityName, @Query("units") String units, @Query("APPID") String appId);*/

    /*@GET("/data/2.5/weather")
    Call<WeatherDataBean> getWeatherFromApiSyncAndAsync(
            @Query("lat") double lat, @Query("lon") double lon, @Query("units") String units, @Query("appid") String appId);*/

    @GET("/data/2.5/weather")
    Call<WeatherDataBean> getWeatherFromApiSyncAndAsync(
            @Query("appid") String appid, @Query("lat") double lat, @Query("lon") double lon);




}
