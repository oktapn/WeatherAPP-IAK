package com.example.okta.day1_iak.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.okta.day1_iak.R;
import com.example.okta.day1_iak.database.ForecastDB;
import com.example.okta.day1_iak.model.forecast.List;
import com.example.okta.day1_iak.widget.Constant;
import com.squareup.picasso.Picasso;

/**
 * Created by Okta on 27/01/2018.
 */

public class ForecastAdapterDB extends  RecyclerView.Adapter<ForecastAdapterDB.CategoryViewHolder>{
    private Context context;
    private java.util.List<ForecastDB> ListForecast;

    public void setListForecast(java.util.List<ForecastDB> listForecast) {
        ListForecast = listForecast;
    }

    public ForecastAdapterDB(Context context) {
        this.context = context;
    }

    @Override
    public ForecastAdapterDB.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new ForecastAdapterDB.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastAdapterDB.CategoryViewHolder holder, int position) {
        String tanggal = getListForecast().get(position).getDate();

        holder.tvDay.setText(tanggal);
        holder.tvDesc.setText(getListForecast().get(position).getDesc());
        holder.tvMax.setText(getListForecast().get(position).getTemp_max());
        holder.tvMin.setText(getListForecast().get(position).getTemp_min());

        Picasso.with(context).load(getListForecast().get(position).getImg_url())
                .placeholder(android.R.color.darker_gray)
                .error(R.mipmap.ic_launcher)
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return getListForecast().size();
    }

    public java.util.List<ForecastDB> getListForecast() {
        return ListForecast;
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay, tvDesc, tvMin, tvMax;
        ImageView icon;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.itemDay);
            tvDesc = itemView.findViewById(R.id.itemDescription);
            tvMax = itemView.findViewById(R.id.itemTempMax);
            tvMin = itemView.findViewById(R.id.itemTempMin);
            icon = itemView.findViewById(R.id.itemIcon);

        }
    }

}
