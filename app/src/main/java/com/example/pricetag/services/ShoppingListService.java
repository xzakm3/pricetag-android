package com.example.pricetag.services;

import com.example.pricetag.data.model.ShoppingList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShoppingListService {
    @GET("/shopping_lists")
    Call<List<ShoppingList>> getShoppingLists();
}
