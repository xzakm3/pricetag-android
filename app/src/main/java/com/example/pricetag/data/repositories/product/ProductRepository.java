package com.example.pricetag.data.repositories.product;

import android.widget.Toast;

import com.example.pricetag.activities.ApplicationWrapper;
import com.example.pricetag.data.interfaces.ItemCallbacks;
import com.example.pricetag.data.model.Product;
import com.example.pricetag.services.ProductService;
import com.example.pricetag.services.RetrofitInstance;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private static final ProductService service = RetrofitInstance.get().create(ProductService.class);

    public static void getProducts(ItemCallbacks callbacks) {
        Call<List<Product>> call = service.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    callbacks.setItemData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toasty.error(ApplicationWrapper.getAppContext(), t.getMessage(), Toast.LENGTH_SHORT, true).show();
            }

        });
    }
}
