
package com.example.okta.day1_iak.model.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<com.example.okta.day1_iak.model.forecast.List> list = null;
    @SerializedName("city")
    @Expose
    private com.example.okta.day1_iak.model.forecast.City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<com.example.okta.day1_iak.model.forecast.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.example.okta.day1_iak.model.forecast.List> list) {
        this.list = list;
    }

    public com.example.okta.day1_iak.model.forecast.City getCity() {
        return city;
    }

    public void setCity(com.example.okta.day1_iak.model.forecast.City city) {
        this.city = city;
    }

}
