package com.example.minchev.dailysmarts.Api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.minchev.dailysmarts.Model.Quote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static final String UNITS_METRIC = "metric";

    private static Api instance;
    private final QuoteService service;

    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    private Api() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(QuoteService.BASE_URL)
                .build();

        service = retrofit.create(QuoteService.class);
    }

    public void getDailyQuote(String method, String format, String lang, final DataListener<Quote> listener) {

        service.getQuote(method, format, lang).enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(@NonNull Call<Quote> call, @NonNull Response<Quote> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onError();
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                listener.onError();
                Log.e("TAG", "getDailyForecast", t);
            }
        });
    }


    public interface DataListener<T> {
        void onSuccess(T data);

        void onError();
    }

}