package com.example.pricetag.services;

import com.example.pricetag.data.model.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShopService {

    @GET("/shops")
    Call<List<Shop>> getShops();
}

