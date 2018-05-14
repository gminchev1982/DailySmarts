package com.example.minchev.dailysmarts.Api;

import com.example.minchev.dailysmarts.Model.Quote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuoteService {
    public static final String BASE_URL = "http://api.forismatic.com/api/1.0/";
    @GET("index")

    //?method=getQuote&amp;format=json
    Call<Quote> getQuote(@Query("method") String method, @Query("format") String format, @Query("lang") String lang);

}

