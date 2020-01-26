package com.example.pricetag.data.repositories;

import android.widget.Toast;

import com.example.pricetag.activities.ApplicationWrapper;
import com.example.pricetag.data.interfaces.CalculateCallbacks;
import com.example.pricetag.data.interfaces.Deletable;
import com.example.pricetag.data.requests.CalculateItemResponse;
import com.example.pricetag.data.requests.CalculateItemsRequest;
import com.example.pricetag.services.ItemService;
import com.example.pricetag.services.RetrofitInstance;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {
    private static final ItemService service = RetrofitInstance.get().create(ItemService.class);

    public static void deleteItem(int id, String entity, Deletable callbacks) {
        Call<Void> call = service.deleteItem(entity, id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toasty.success(ApplicationWrapper.getAppContext(), "Item was deleted successfuly!", Toast.LENGTH_SHORT, true).show();
                    callbacks.afterDelete(id);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toasty.error(ApplicationWrapper.getAppContext(), t.getMessage(), Toast.LENGTH_SHORT, true).show();
            }

        });
    }


    public static void calculate(CalculateItemsRequest request, CalculateCallbacks callbacks) {
        Call<List<CalculateItemResponse>> call = service.calculate(request);

        call.enqueue(new Callback<List<CalculateItemResponse>>() {
            @Override
            public void onResponse(Call<List<CalculateItemResponse>> call, Response<List<CalculateItemResponse>> response) {
                if (response.isSuccessful()) {
                    callbacks.setCalculateData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CalculateItemResponse>> call, Throwable t) {
                Toasty.error(ApplicationWrapper.getAppContext(), t.getMessage(), Toast.LENGTH_SHORT, true).show();
            }

        });
    }
}
