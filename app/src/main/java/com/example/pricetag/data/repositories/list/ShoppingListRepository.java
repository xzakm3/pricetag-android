package com.example.pricetag.data.repositories.list;

import android.view.View;
import android.widget.Toast;

import com.example.pricetag.activities.ApplicationWrapper;
import com.example.pricetag.data.interfaces.ItemCallbacks;
import com.example.pricetag.data.model.ShoppingList;
import com.example.pricetag.data.requests.ProductRequest;
import com.example.pricetag.data.requests.ShoppingListRequest;
import com.example.pricetag.services.RetrofitInstance;
import com.example.pricetag.services.ShoppingListService;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingListRepository {

    private static final ShoppingListService service = RetrofitInstance.get().create(ShoppingListService.class);

    public static void getShoppingLists(ItemCallbacks callbacks) {
        Call<List<ShoppingList>> call = service.getShoppingLists();

        call.enqueue(new Callback<List<ShoppingList>>() {
            @Override
            public void onResponse(Call<List<ShoppingList>> call, Response<List<ShoppingList>> response) {
                if (response.isSuccessful()) {
                    callbacks.setItemData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ShoppingList>> call, Throwable t) {
                Toasty.error(ApplicationWrapper.getAppContext(), t.getMessage(), Toast.LENGTH_SHORT, true).show();
            }

        });
    }

    public static void getShoppingList(int id, ItemCallbacks callbacks) {
        Call<ShoppingList> call = service.getShoppingList(id);

        call.enqueue(new Callback<ShoppingList>() {
            @Override
            public void onResponse(Call<ShoppingList> call, Response<ShoppingList> response) {
                if (response.isSuccessful()) {
                    callbacks.afterItemLoad(response.body());
                }
            }

            @Override
            public void onFailure(Call<ShoppingList> call, Throwable t) {
                Toasty.error(ApplicationWrapper.getAppContext(), t.getMessage(), Toast.LENGTH_SHORT, true).show();
            }

        });
    }

    public static void updateShoppingList(int id, ShoppingListRequest item, ItemCallbacks callbacks, View view) {
        Call<Void> call = service.updateShoppingList(id, item);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callbacks.afterUpdate(view);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toasty.error(ApplicationWrapper.getAppContext(), t.getMessage(), Toast.LENGTH_SHORT, true).show();
            }

        });
    }

    public static void createShoppingList(ShoppingListRequest item, ItemCallbacks callbacks, View view) {
        Call<Void> call = service.createShoppingList(item);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callbacks.afterCreate(view);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toasty.error(ApplicationWrapper.getAppContext(), t.getMessage(), Toast.LENGTH_SHORT, true).show();
            }

        });
    }

}
