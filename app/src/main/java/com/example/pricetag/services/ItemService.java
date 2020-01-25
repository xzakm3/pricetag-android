package com.example.pricetag.services;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface ItemService {

    @DELETE("/{entity}/{id}")
    Call<Void> deleteItem(@Path("entity") String entity, @Path("id") int id);
}
