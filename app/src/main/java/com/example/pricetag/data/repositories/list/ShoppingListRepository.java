package com.example.pricetag.data.repositories.list;

import android.widget.Toast;

import com.example.pricetag.activities.ApplicationWrapper;
import com.example.pricetag.data.interfaces.ItemCallbacks;
import com.example.pricetag.data.model.ShoppingList;
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

}
