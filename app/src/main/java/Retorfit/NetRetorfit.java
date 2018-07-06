package Retorfit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetRetorfit{
    private static NetRetorfit ourInstance = new NetRetorfit();
    public static NetRetorfit getOurInstance(){
        return ourInstance;
    }

    private NetRetorfit(){
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitService service = retrofit.create(RetrofitService.class);

    public RetrofitService getService() {
        return service;
    }





}
