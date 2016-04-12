package com.example.vladimir.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vladimir.weather.App;
import com.example.vladimir.weather.R;
import com.example.vladimir.weather.model.WeatherData5DTO;
import com.example.vladimir.weather.util.AppUtils;
import com.example.vladimir.weather.util.LogWrapper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherDataAdapter extends RecyclerView.Adapter<WeatherDataAdapter.ViewHolder> {

    public static final String TAG = "WeatherDataAdapter";
    private List<WeatherData5DTO> itemsData;

    public WeatherDataAdapter(List<WeatherData5DTO> itemsData) {
        this.itemsData = itemsData;
    }

    @Override
    public WeatherDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data_weather, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.day.setText(AppUtils.parseDateToDay(itemsData.get(position).getDateText()));
        String condition = itemsData.get(position).getWeather().get(0).getDescription();
        String icon = itemsData.get(position).getWeather().get(0).getIcon();
        viewHolder.condition.setText(condition);
        viewHolder.temp.setText((int) itemsData.get(position).getMain().getTemp_max() + " \u2103" + " / " + (int) itemsData.get(position).getMain().getTemp_min() + " \u2103");
        viewHolder.icon.setImageResource(App.mContext.getResources().getIdentifier(
                "a_" + icon, "drawable", App.mContext.getPackageName()));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.day)
        TextView day;
        @Bind(R.id.condition)
        TextView condition;
        @Bind(R.id.temp_maxmin)
        TextView temp;
        @Bind(R.id.image)
        ImageView icon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            ButterKnife.bind(this, itemLayoutView);
        }
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }
}