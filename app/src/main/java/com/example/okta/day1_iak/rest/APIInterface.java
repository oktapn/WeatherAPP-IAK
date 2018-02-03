package com.example.okta.day1_iak.rest;

import com.example.okta.day1_iak.model.forecast.Forecast;
import com.example.okta.day1_iak.model.weather.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Okta on 20/01/2018.
 */

public interface APIInterface {
    @GET("weather")
    Call<WeatherModel> getWeather(@Query("q") String kota,
                                  @Query("units") String satuan,
                                  @Query("appid") String api_key);
    @GET("forecast")
    Call<Forecast> getForecast(@Query("q") String kota,
                               @Query("units") String satuan,
                               @Query("appid") String api_key);
}
