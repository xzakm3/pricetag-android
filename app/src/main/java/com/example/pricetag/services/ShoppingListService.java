package com.example.pricetag.services;

import com.example.pricetag.data.model.ShoppingList;
import com.example.pricetag.data.requests.ShoppingListRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ShoppingListService {
    @GET("/shopping_lists")
    Call<List<ShoppingList>> getShoppingLists();

    @POST("/shopping_lists")
    Call<Void> createShoppingList(@Body ShoppingListRequest itemRequest);

    @PUT("/shopping_lists/{id}")
    Call<Void> updateShoppingList(@Path("id") int id, @Body ShoppingListRequest itemRequest);

    @GET("/shopping_lists/{id}")
    Call<ShoppingList> getShoppingList(@Path("id") int id);
}
