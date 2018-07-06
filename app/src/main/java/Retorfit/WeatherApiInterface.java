package Retorfit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherApiInterface {
    @Headers({"Accept: application/json"})
    @GET("weather/current/hourly")
    Call<WeatherRepo> get_Weather_retrofit(@Query("version") int version, @Query("lat") String lat, @Query("lon") String lon);




}
