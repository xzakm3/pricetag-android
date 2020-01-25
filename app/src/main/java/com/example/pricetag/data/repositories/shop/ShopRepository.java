package com.example.pricetag.data.repositories.shop;

import android.widget.Toast;

import com.example.pricetag.activities.ApplicationWrapper;
import com.example.pricetag.data.interfaces.ItemCallbacks;
import com.example.pricetag.data.model.Shop;
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
}
