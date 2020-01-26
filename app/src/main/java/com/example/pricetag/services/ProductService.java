package com.example.pricetag.services;

import com.example.pricetag.data.model.Product;
import com.example.pricetag.data.requests.ProductRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {

    @GET("/products")
    Call<List<Product>> getProducts();

    @POST("/products")
    Call<Void> createProduct(@Body ProductRequest itemRequest);

    @PUT("/products/{id}")
    Call<Void> updateProduct(@Path("id") int id, @Body ProductRequest itemRequest);

    @GET("/products/{id}")
    Call<Product> getProduct(@Path("id") int id);
}

