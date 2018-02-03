package com.example.okta.day1_iak.helper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Okta on 21/01/2018.
 */

public class ParcelForecast implements Parcelable {
    String date, tempmax, tempmin, desc, icon, wind, humidity, degree, pressure;

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTempmax() {
        return tempmax;
    }

    public void setTempmax(String tempmax) {
        this.tempmax = tempmax;
    }

    public String getTempmin() {
        return tempmin;
    }

    public void setTempmin(String tempmin) {
        this.tempmin = tempmin;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public ParcelForecast() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.tempmax);
        dest.writeString(this.tempmin);
        dest.writeString(this.desc);
        dest.writeString(this.icon);
        dest.writeString(this.wind);
        dest.writeString(this.humidity);
        dest.writeString(this.degree);
        dest.writeString(this.pressure);
    }

    protected ParcelForecast(Parcel in) {
        this.date = in.readString();
        this.tempmax = in.readString();
        this.tempmin = in.readString();
        this.desc = in.readString();
        this.icon = in.readString();
        this.wind = in.readString();
        this.humidity = in.readString();
        this.degree = in.readString();
        this.pressure = in.readString();
    }

    public static final Creator<ParcelForecast> CREATOR = new Creator<ParcelForecast>() {
        @Override
        public ParcelForecast createFromParcel(Parcel source) {
            return new ParcelForecast(source);
        }

        @Override
        public ParcelForecast[] newArray(int size) {
            return new ParcelForecast[size];
        }
    };
}
