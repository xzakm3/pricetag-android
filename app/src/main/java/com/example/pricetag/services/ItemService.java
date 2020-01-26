package com.example.pricetag.services;

import com.example.pricetag.data.requests.CalculateItemResponse;
import com.example.pricetag.data.requests.CalculateItemsRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ItemService {

    @DELETE("/{entity}/{id}")
    Call<Void> deleteItem(@Path("entity") String entity, @Path("id") int id);

    @POST("/calculate")
    Call<List<CalculateItemResponse>> calculate(@Body CalculateItemsRequest calculateItemsRequest);
}
