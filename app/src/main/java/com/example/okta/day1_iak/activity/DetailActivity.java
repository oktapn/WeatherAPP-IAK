package com.example.okta.day1_iak.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.okta.day1_iak.R;
import com.example.okta.day1_iak.helper.ParcelForecast;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    TextView tvTodayDetail,tvDateDetail,tvTempDetail, humidity, pressure, wind;
    ImageView ivIconWeatherDetail;

    ParcelForecast parcelForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupView();

        parcelForecast = getIntent().getParcelableExtra("key");

        tvTempDetail.setText(parcelForecast.getTempmax());
        tvDateDetail.setText(parcelForecast.getDate());
        humidity.setText(parcelForecast.getHumidity());
        pressure.setText(parcelForecast.getPressure());
        wind.setText(parcelForecast.getWind());
        Picasso.with(this).load(parcelForecast.getIcon()).error(R.mipmap.ic_launcher).placeholder(android.R.color.darker_gray).into(ivIconWeatherDetail);

    }

    private void setupView() {
        tvTodayDetail=findViewById(R.id.tvTodayDetail);
        tvDateDetail =findViewById(R.id.tvDateDetail);
        tvTempDetail=findViewById(R.id.tvTempDetail);
        humidity =findViewById(R.id.tvWeatherDetail1);
        pressure =findViewById(R.id.tvWeatherDetail2);
        wind =findViewById(R.id.tvWeatherDetail3);
        ivIconWeatherDetail =findViewById(R.id.ivImageDetail);
    }
}
