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

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<WeatherData5DTO> itemsData;
    private Context context;

    public MyAdapter(List<WeatherData5DTO> itemsData,Context context) {
        this.itemsData = itemsData;
        this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data_weather, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.day.setText(itemsData.get(position).getDate() + "");
        // viewHolder.condition.setImageResource(itemsData[position].getImageUrl());

        String condition = itemsData.get(position).getWeather().get(0).getDescription();
        String icon = itemsData.get(position).getWeather().get(0).getIcon();
        viewHolder.condition.setText(condition);
        viewHolder.temp.setText(itemsData.get(position).getMain().getTemp_max() + " \u2103" + " / " + itemsData.get(position).getMain().getTemp_min() + " \u2103");
        viewHolder.icon.setImageResource(context.getResources().getIdentifier(
                "a_" + icon, "drawable", context.getPackageName()));

    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView day;
        public TextView condition;
        public TextView temp;
        public ImageView icon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            day = (TextView) itemLayoutView.findViewById(R.id.day);
            condition = (TextView) itemLayoutView.findViewById(R.id.condition);
            temp = (TextView) itemLayoutView.findViewById(R.id.temp_maxmin);

            icon = (ImageView) itemLayoutView.findViewById(R.id.image);
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemsData.size();
    }
}