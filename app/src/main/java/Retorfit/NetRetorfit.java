package Retorfit;

import retrofit2.Retrofit;

public class NetRetorfit{
    private  static NetRetorfit ourInstance = new NetRetorfit();
    public static NetRetorfit getOurInstance(){
        return ourInstance;
    }

    private NetRetorfit(){

    }


}
