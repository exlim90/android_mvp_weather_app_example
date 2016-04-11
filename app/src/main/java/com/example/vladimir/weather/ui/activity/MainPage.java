package com.example.vladimir.weather.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vladimir.weather.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainPage extends Activity {


    //MyObject object = new MyObject();
    private static final String TAG = "MainPage";
    public static final String API_URL_MAIN = "http://api.openweathermap.org/data/2.5/weather?q=[city]&appid=5ae54136c3bce516d6b06adf29a91489&units=metric";
    public static final String API_URL_FORECASt = "http://api.openweathermap.org/data/2.5/forecast?q=[city]&mode=json&appid=5ae54136c3bce516d6b06adf29a91489&units=metric";


    @Bind(R.id.edt_cityName)
    EditText edt_cityName;

    @Bind(R.id.btn_enter)
    Button btn_enter;

    @Bind(R.id.txt_result)
    TextView txt_result;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Log.d(TAG, "onCreate called");
        context = this;
        ButterKnife.bind(this);


        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "klikna ENtER :))");
                if (edt_cityName.getText().toString().equals("")) {
                    Toast.makeText(context, "Please enter city name !!!", Toast.LENGTH_LONG).show();
                } else {
                    //Toast.makeText(context,edt_cityName.getText().toString(),Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainPage.this, WeatherDetailsActivity.class);
                    intent.putExtra("cityNameExtraParam", edt_cityName.getText().toString());
                    startActivity(intent);

                    //GetWeatherData request = new GetWeatherData(txt_result);
                    //request.execute(API_URL.replace("[city]", edt_cityName.getText().toString()));
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
