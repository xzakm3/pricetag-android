package com.example.pricetag.services;

import com.example.pricetag.data.model.Product;
import com.example.pricetag.data.requests.ProductRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductService {

    @GET("/products")
    Call<List<Product>> getProducts();

    @POST("/products")
    Call<Void> createProduct(@Body ProductRequest itemRequest);
}

