
package com.example.okta.day1_iak.model.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("main")
    @Expose
    private com.example.okta.day1_iak.model.forecast.Main main;
    @SerializedName("weather")
    @Expose
    private java.util.List<com.example.okta.day1_iak.model.forecast.Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    private com.example.okta.day1_iak.model.forecast.Clouds clouds;
    @SerializedName("wind")
    @Expose
    private com.example.okta.day1_iak.model.forecast.Wind wind;
    @SerializedName("sys")
    @Expose
    private com.example.okta.day1_iak.model.forecast.Sys sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;
    @SerializedName("rain")
    @Expose
    private com.example.okta.day1_iak.model.forecast.Rain rain;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public com.example.okta.day1_iak.model.forecast.Main getMain() {
        return main;
    }

    public void setMain(com.example.okta.day1_iak.model.forecast.Main main) {
        this.main = main;
    }

    public java.util.List<com.example.okta.day1_iak.model.forecast.Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<com.example.okta.day1_iak.model.forecast.Weather> weather) {
        this.weather = weather;
    }

    public com.example.okta.day1_iak.model.forecast.Clouds getClouds() {
        return clouds;
    }

    public void setClouds(com.example.okta.day1_iak.model.forecast.Clouds clouds) {
        this.clouds = clouds;
    }

    public com.example.okta.day1_iak.model.forecast.Wind getWind() {
        return wind;
    }

    public void setWind(com.example.okta.day1_iak.model.forecast.Wind wind) {
        this.wind = wind;
    }

    public com.example.okta.day1_iak.model.forecast.Sys getSys() {
        return sys;
    }

    public void setSys(com.example.okta.day1_iak.model.forecast.Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public com.example.okta.day1_iak.model.forecast.Rain getRain() {
        return rain;
    }

    public void setRain(com.example.okta.day1_iak.model.forecast.Rain rain) {
        this.rain = rain;
    }

}
