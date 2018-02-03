package com.example.okta.day1_iak.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okta.day1_iak.adapter.ForecastAdapterDB;
import com.example.okta.day1_iak.database.ForecastDB;
import com.example.okta.day1_iak.database.ForecastHelper;
import com.example.okta.day1_iak.widget.Constant;
import com.example.okta.day1_iak.R;
import com.example.okta.day1_iak.adapter.AdapterForecast;
import com.example.okta.day1_iak.helper.ItemClickView;
import com.example.okta.day1_iak.helper.ParcelForecast;
import com.example.okta.day1_iak.model.forecast.Forecast;
import com.example.okta.day1_iak.model.weather.WeatherModel;
import com.example.okta.day1_iak.rest.APIClient;
import com.example.okta.day1_iak.rest.APIInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvForecast;
    TextView tvTempMin, tvTempMax, tvDesc, tvDay;
    ImageView imgIcon;

    ForecastHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        db = new ForecastHelper(MainActivity.this);
        getForecastDataDb();
        getCurrentWeather("jakarta", "metric", "92fb592055dd5980992e05c2c42c088d");
//        getDataForecast("jakarta", "metric", "92fb592055dd5980992e05c2c42c088d");
    }

    private void setupView() {
        rvForecast = (RecyclerView) findViewById(R.id.rvForcast);
        tvTempMin = (TextView) findViewById(R.id.tvTempMin);
        tvTempMax = (TextView) findViewById(R.id.tvTempMax);
        tvDesc = (TextView) findViewById(R.id.tvDesc);
        tvDay = (TextView) findViewById(R.id.TVTodayDays);
        imgIcon = (ImageView) findViewById(R.id.imgIcon);
    }

    private void getDataForecast(String kota, String units, String ApiKey) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Forecast> forecastCall = apiInterface.getForecast(kota, units, ApiKey);
        forecastCall.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                try {
                    final List<com.example.okta.day1_iak.model.forecast.List> lists = response.body().getList();
                    AdapterForecast adapterForecast = new AdapterForecast(MainActivity.this);
                    adapterForecast.setListForecast(lists);
                    rvForecast.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    rvForecast.setAdapter(adapterForecast);
                    ItemClickView.addTo(rvForecast).setOnItemClickListener(new ItemClickView.OnItemClickListener() {
                        @Override
                        public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            ParcelForecast parcelForecast = new ParcelForecast();
                            parcelForecast.setDate(Constant.getDate(lists.get(position).getDt()));
                            parcelForecast.setDesc(lists.get(position).getWeather().get(0).getDescription());
                            parcelForecast.setTempmin(String.valueOf(lists.get(position).getMain().getTempMin()));
                            parcelForecast.setTempmax(String.valueOf(lists.get(position).getMain().getTempMax()));
                            parcelForecast.setWind(String.valueOf(lists.get(position).getWind().getSpeed()));
                            parcelForecast.setHumidity(String.valueOf(lists.get(position).getMain().getHumidity()));
                            parcelForecast.setPressure(String.valueOf(lists.get(position).getMain().getPressure()));
                            parcelForecast.setDegree(String.valueOf(lists.get(position).getWind().getDeg()));
                            String img_url = String.format("http://openweathermap.org/img/w/%s.png", lists.get(position).getWeather().get(0).getIcon());
                            parcelForecast.setIcon(img_url);
                            intent.putExtra("key", parcelForecast);
                            startActivity(intent);
                        }
                    });
                } catch (NullPointerException e) {
                    Log.e("Forecast", "onResponse", e);
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", t.getMessage());
            }
        });
    }

    private void getForecastDataDb() {
        List<ForecastDB> list = db.getAllForecast();
        ForecastAdapterDB adapter = new ForecastAdapterDB(MainActivity.this);
        adapter.setListForecast(list);
        rvForecast.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvForecast.setAdapter(adapter);
    }

    private void getCurrentWeather(String kota, String units, String ApiKey) {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Proses Mengambil Data");
        progressDialog.show();
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<WeatherModel> weatherCall = apiInterface.getWeather(kota, units, ApiKey);
        weatherCall.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                progressDialog.dismiss();
                String temp = String.valueOf(response.body().getMain().getTemp());
                String day = Constant.getDate(response.body().getDt());
                String tanggal = Constant.getDate(response.body().getDt());
                tvDay.setText(tanggal);

                tvDay.setText(response.body().getName() + "\n" + day);
                tvDesc.setText(response.body().getWeather().get(0).getMain());
//                tvTempMax.setText(getResources().getString(R.string.format_temperature));
////                tvTempMax.setText(getResources().getString(R.string.));
                tvTempMax.setText(String.valueOf(response.body().getMain().getTempMax()));
                tvTempMin.setText(String.valueOf(response.body().getMain().getTempMin()));
                String imgurl = "http://openweathermap.org/img/w/" + response.body().getWeather().get(0).getIcon() + ".png";
                Picasso.with(MainActivity.this)
                        .load(imgurl)
                        .placeholder(android.R.color.darker_gray)
                        .error(R.mipmap.ic_launcher)
                        .into(imgIcon);
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
