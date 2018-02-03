package com.example.okta.day1_iak.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.okta.day1_iak.R;
import com.example.okta.day1_iak.database.ForecastDB;
import com.example.okta.day1_iak.database.ForecastHelper;
import com.example.okta.day1_iak.model.forecast.Forecast;
import com.example.okta.day1_iak.rest.APIClient;
import com.example.okta.day1_iak.rest.APIInterface;
import com.example.okta.day1_iak.widget.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Splashscreen extends AppCompatActivity {

    ForecastHelper db;
    final static String API_KEY = "92fb592055dd5980992e05c2c42c088d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getForecastData(API_KEY);
        db = new ForecastHelper(Splashscreen.this);
    }

    private void getForecastData(String ApiKey) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Forecast> call = apiInterface.getForecast("jakarta", "metric", ApiKey);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                try {
                    final List<com.example.okta.day1_iak.model.forecast.List> list = response.body().getList();
                    for (int i = 0; i < list.size(); i++) {
                        db.addForecast(new ForecastDB(
                                Constant.getDate(list.get(i).getDt()),
                                String.valueOf(list.get(i).getMain().getTempMax()),
                                String.valueOf(list.get(i).getMain().getTempMin()),
                                list.get(i).getWeather().get(0).getDescription(),
                                String.format("http://openweathermap.org/img/w/%s.png", list.get(i).getWeather().get(0).getIcon())
                        ));
                    }

                    startActivity(new Intent(Splashscreen.this, MainActivity.class));
                    finish();

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
