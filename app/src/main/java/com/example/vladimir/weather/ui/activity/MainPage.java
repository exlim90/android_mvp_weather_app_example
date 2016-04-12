package com.example.vladimir.weather.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vladimir.weather.App;
import com.example.vladimir.weather.R;
import com.example.vladimir.weather.api.ApiService;
import com.example.vladimir.weather.model.WeatherData5DTO;
import com.example.vladimir.weather.model.WeatherResponse5DTO;
import com.example.vladimir.weather.mvp.presenter.MainPagePresenter;
import com.example.vladimir.weather.mvp.presenter.MainPagePresenterImp;
import com.example.vladimir.weather.mvp.view.MainPageView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainPage extends Activity implements MainPageView<WeatherResponse5DTO>, View.OnClickListener {

    private static final String TAG = "MainPage";
    public static final String API_URL_MAIN = "http://api.openweathermap.org/data/2.5/weather?q=[city]&appid=5ae54136c3bce516d6b06adf29a91489&units=metric";
    public static final String API_URL_FORECAST = "http://api.openweathermap.org/data/2.5/forecast?q=[city]&mode=json&appid=5ae54136c3bce516d6b06adf29a91489&units=metric";

    @Inject
    ApiService apiService;

    @Bind(R.id.edt_cityName)
    EditText edt_cityName;

    @Bind(R.id.btn_enter)
    Button btn_enter;

    @Bind(R.id.txt_result)
    TextView txt_result;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    Context context;

    private MainPagePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Log.d(TAG, "onCreate called");
        App.getComponent().inject(this);

        context = this;
        ButterKnife.bind(this);
        btn_enter.setOnClickListener(this);
        presenter = new MainPagePresenterImp(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setCityError() {
        edt_cityName.setError(getString(R.string.hint_enter_city_name));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateWeatherDetails(WeatherResponse5DTO result) {
        Log.d(TAG, "result=" + result);
        Intent intent = new Intent(MainPage.this, WeatherDetailsActivity.class);
        intent.putExtra("cityNameExtraParam", edt_cityName.getText().toString());
        intent.putExtra("result", result);
        startActivity(intent);
        //finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_enter:
                Log.d(TAG, "onClick btn_enter");
                hideKeyboard();
                presenter.validateCity(edt_cityName.getText().toString());
                break;
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
