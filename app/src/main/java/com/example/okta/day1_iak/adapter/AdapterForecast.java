package com.example.okta.day1_iak.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.okta.day1_iak.widget.Constant;
import com.example.okta.day1_iak.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Okta on 21/01/2018.
 */

public class AdapterForecast extends RecyclerView.Adapter<AdapterForecast.CategoryViewHolder> {
    private Context context;
    private List<com.example.okta.day1_iak.model.forecast.List> ListForecast;

    public void setListForecast(List<com.example.okta.day1_iak.model.forecast.List> listForecast) {
        ListForecast = listForecast;
    }

    public AdapterForecast(Context context) {
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        String tanggal = Constant.getDate(getListForecast().get(position).getDt());
        String description = getListForecast().get(position).getWeather().get(0).getDescription();
        String maxtemp = String.valueOf(getListForecast().get(position).getMain().getTempMax());
        String mintemp = String.valueOf(getListForecast().get(position).getMain().getTempMin());
        holder.tvDay.setText(tanggal);
        holder.tvDesc.setText(description);
        holder.tvMax.setText(maxtemp);
        holder.tvMin.setText(mintemp);

        String img_url = "http://openweathermap.org/img/w/" + getListForecast().get(position).getWeather().get(0).getIcon() + ".png";
        Picasso.with(context).load(img_url)
                .placeholder(android.R.color.darker_gray)
                .error(R.mipmap.ic_launcher)
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return getListForecast().size();
    }

    public List<com.example.okta.day1_iak.model.forecast.List> getListForecast() {
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
