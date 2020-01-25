package com.example.pricetag.services;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    //private static final String BASE_URL = "https://polar-wildwood-72730.herokuapp.com/";
    private static final String BASE_URL = "http://10.0.2.2:3000";

    public static Retrofit get() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}