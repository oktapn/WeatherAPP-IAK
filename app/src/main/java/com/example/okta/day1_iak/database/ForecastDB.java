package com.example.okta.day1_iak.database;

/**
 * Created by Okta on 27/01/2018.
 */

public class ForecastDB {
    private String date;
    private String temp_max;
    private String temp_min;
    private String desc;
    private String img_url;

    private int id;

    public ForecastDB() {
    }

    public ForecastDB(String date, String temp_max, String temp_min, String desc, String img_url) {
        this.date = date;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.desc = desc;
        this.img_url = img_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
