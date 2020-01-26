package com.example.pricetag.data.repositories.shop;

import android.view.View;
import android.widget.Toast;

import com.example.pricetag.activities.ApplicationWrapper;
import com.example.pricetag.data.interfaces.ItemCallbacks;
import com.example.pricetag.data.model.Shop;
import com.example.pricetag.data.requests.ShopRequest;
import com.example.pricetag.services.RetrofitInstance;
import com.example.pricetag.services.ShopService;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopRepository {

    private static final ShopService service = RetrofitInstance.get().create(ShopService.class);

    public static void getShops(ItemCallbacks callbacks) {
        Call<List<Shop>> call = service.getShops();

        call.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                if (response.isSuccessful()) {
                    callbacks.setItemData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                Toasty.error(ApplicationWrapper.getAppContext(), t.getMessage(), Toast.LENGTH_SHORT, true).show();
            }

        });
    }

    public static void createShop(ShopRequest item, ItemCallbacks callbacks, View view) {
        Call<Void> call = service.createShop(item);

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


    public static void updateShop(ShopRequest item, ItemCallbacks callbacks, View view) {
        Call<Void> call = service.createShop(item);

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

    public static void getShop(int id, ItemCallbacks callbacks) {
        Call<Shop> call = service.getShops(id);

        call.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                if (response.isSuccessful()) {
                    callbacks.afterItemLoad(response.body());
                }
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                Toasty.error(ApplicationWrapper.getAppContext(), t.getMessage(), Toast.LENGTH_SHORT, true).show();
            }

        });
    }
}
