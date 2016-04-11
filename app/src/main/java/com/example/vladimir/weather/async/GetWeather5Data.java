package com.example.vladimir.weather.async;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vladimir.weather.adapter.MyAdapter;
import com.example.vladimir.weather.model.WeatherResponse5DTO;
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
public class GetWeather5Data extends AsyncTask<String, Integer, String> {

    private TextView txt_result;

    private TextView cityName;
    private ImageView icon;
    private TextView temp;
    private TextView condition;
    private Context context;
    RecyclerView recyclerView;

    public GetWeather5Data(TextView result) {
        this.txt_result = result;
    }

    public GetWeather5Data(Context context,RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView=recyclerView;
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
            WeatherResponse5DTO response = AppUtils.parseWeatherData5(rawJson);
            if(response.getWeatherData5DTOs()!=null){
                MyAdapter adapter=new MyAdapter(response.getWeatherData5DTOs(), context);
                recyclerView.setAdapter(adapter);
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
