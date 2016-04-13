package com.example.vladimir.weather.di;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.vladimir.weather.App;
import com.example.vladimir.weather.api.ApiService;
import com.example.vladimir.weather.util.LogWrapper;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vladimir on 4/12/2016.
 */
@Module
public class ApiModule {
    private static final String OK_HTTP_LOGGER = "OkHttpClientLogger";
    private final App application;

    public ApiModule(App application) {
        this.application = application;
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Singleton
    @Provides
    ApiService provideApiService(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request();
                                Response response = chain.proceed(request);
                                //add headers
                                request.newBuilder().addHeader("Authorization", "sometoken").build();
                                request = chain.request().newBuilder()
                                        .header("Authorization", "sometoken")
                                        .method(chain.request().method(), chain.request().body())
                                        .build();

                                LogWrapper.d(OK_HTTP_LOGGER + " Request = ", request.toString());
                                LogWrapper.d(OK_HTTP_LOGGER + " Request ", "headers=" + request.headers().toString());
                                LogWrapper.d(OK_HTTP_LOGGER + " Response = ", response.toString());
                                LogWrapper.d(OK_HTTP_LOGGER + " Response ", "headers=" + response.headers().toString());

                                boolean unAuthorized = (response.code() == 401);
                                if (unAuthorized) {
                                    //throw new AuthorizeException();
                                }
                                return chain.proceed(request);
                            }
                        }).build();

        return client;
    }
}
