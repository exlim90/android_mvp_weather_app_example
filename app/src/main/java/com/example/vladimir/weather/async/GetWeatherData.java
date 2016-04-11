package com.example.vladimir.weather.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.vladimir.weather.model.WeatherData;
import com.example.vladimir.weather.model.WeatherResponseDTO;
import com.example.vladimir.weather.util.AppUtils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Vladimir on 12.01.2016.
 */
public class GetWeatherData extends AsyncTask<String, Integer, String> {

    private TextView txt_result;

    private TextView cityName;
    private ImageView icon;
    private TextView temp;
    private TextView condition;
    private Context context;

    public GetWeatherData(TextView result) {
        this.txt_result = result;
    }

    public GetWeatherData(Context context, TextView cityName, ImageView icon, TextView temp, TextView condition) {
        this.context = context;
        this.cityName = cityName;
        this.icon = icon;
        this.temp = temp;
        this.condition = condition;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            response = httpclient.execute(new HttpGet(params[0]));
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                responseString = out.toString();
                out.close();
            } else {
                //Closes the connection.
                response.getEntity().getContent().close();
                Log.d("error getting data", statusLine.getReasonPhrase());
                Toast.makeText(context, statusLine.getReasonPhrase(), Toast.LENGTH_LONG).show();
                //throw new IOException(statusLine.getReasonPhrase());
                //responseString = statusLine.getReasonPhrase();
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        } catch (IOException e) {
            //TODO Handle problems..
        }
        return responseString;
    }

    @Override
    protected void onPostExecute(String rawJson) {
        Log.d("GetWeatherData", rawJson);
        if (txt_result != null && rawJson != null) {
            txt_result.setText(rawJson);
        }


        if (rawJson != null && checkIfCityExist(rawJson)) {
            WeatherResponseDTO response = AppUtils.parseWeatherData(rawJson);
            cityName.setText(response.getCityName());

            temp.setText(response.getMain().getTemp() + " \u2103");

            if (response.getWeatherData() != null && response.getWeatherData().get(0) != null) {
                WeatherData data = response.getWeatherData().get(0);

                condition.setText(data.getMain());
                icon.setImageResource(context.getResources().getIdentifier(
                        "a_" + data.getIcon(), "drawable", context.getPackageName()));
            }
        } else {
            Toast.makeText(context, "City does not exist !!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkIfCityExist(String rawJson) {
        boolean exist = false;
        try {
            JSONObject errorObjectJson = new JSONObject(rawJson);
            int code = errorObjectJson.getInt("cod");
            if (code == 200) {
                exist = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return exist;
    }


}
