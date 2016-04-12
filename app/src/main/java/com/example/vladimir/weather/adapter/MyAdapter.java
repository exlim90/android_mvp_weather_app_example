package com.example.vladimir.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vladimir.weather.R;
import com.example.vladimir.weather.model.WeatherData5DTO;
import com.example.vladimir.weather.util.AppUtils;
import com.example.vladimir.weather.util.LogWrapper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public static final String TAG = "MyAdapter";
    private List<WeatherData5DTO> itemsData;
    private Context context;

    public MyAdapter(List<WeatherData5DTO> itemsData, Context context) {
        this.itemsData = itemsData;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data_weather, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.day.setText(AppUtils.parseDateToDay(itemsData.get(position).getDate()));
        LogWrapper.d(TAG, itemsData.get(position).getDate() + " >> " + AppUtils.parseDateToDDmmYYYY(itemsData.get(position).getDate()));

        String condition = itemsData.get(position).getWeather().get(0).getDescription();
        String icon = itemsData.get(position).getWeather().get(0).getIcon();
        viewHolder.condition.setText(condition);
        viewHolder.temp.setText(itemsData.get(position).getMain().getTemp_max() + " \u2103" + " / " + itemsData.get(position).getMain().getTemp_min() + " \u2103");
        viewHolder.icon.setImageResource(context.getResources().getIdentifier(
                "a_" + icon, "drawable", context.getPackageName()));

    }

    // inner class to hold a reference to each item of RecyclerView
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