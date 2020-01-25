package com.example.pricetag.services;


import com.example.pricetag.utils.Preferences;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static Retrofit retrofitAuthorized;


    //private static final String BASE_URL = "https://polar-wildwood-72730.herokuapp.com/";
    private static final String BASE_URL = "http://10.0.2.2:3000";

    public static Retrofit getBaseInstance() {

        if (retrofit == null) {
            retrofit = getBaseBuilder().build();
        }
        return retrofit;
    }

    public static Retrofit get() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        String token = Preferences.getAccessToken();

        if (token != null) {
            httpClient.addInterceptor(chain -> {
                Request request = chain.request().newBuilder().addHeader("Authorization", token).build();
                return chain.proceed(request);
            });
        }

        if (retrofitAuthorized == null) {
            retrofitAuthorized = getBaseBuilder().client(httpClient.build()).build();
        }

        return retrofitAuthorized;
    }

    private static Retrofit.Builder getBaseBuilder() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
    }
}
