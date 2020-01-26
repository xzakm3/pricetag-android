package com.example.pricetag.services;

import com.example.pricetag.data.model.Shop;
import com.example.pricetag.data.requests.ShopRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ShopService {

    @GET("/shops")
    Call<List<Shop>> getShops();

    @POST("/shops")
    Call<Void> createShop(@Body ShopRequest shopRequest);

}

