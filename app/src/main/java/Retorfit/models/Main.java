package Retorfit.models;

import android.content.Context;

public class Main {

    Double temp;
    Integer pressure;
    Integer humidity;
    Double temp_min;
    Double temp_max;

    String country;
    String name;

    public Double getTemp() {
        Double fixtemp = temp- 273.15;
        Double result = (double) Math.round(fixtemp *10)/10;
        return result;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }


    public String getCountry(){
        return country;
    }

    public String getCity(){
        return name;
    }

}
