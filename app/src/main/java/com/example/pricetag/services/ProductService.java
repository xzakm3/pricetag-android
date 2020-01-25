package com.example.pricetag.services;

import com.example.pricetag.data.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    @GET("/products")
    Call<List<Product>> getProducts();
}

