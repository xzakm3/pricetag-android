package com.example.pricetag.data.repositories.product;

import android.view.View;
import android.widget.Toast;

import com.example.pricetag.activities.ApplicationWrapper;
import com.example.pricetag.data.interfaces.ItemCallbacks;
import com.example.pricetag.data.model.Product;
import com.example.pricetag.data.requests.ProductRequest;
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

    public static void createProduct(ProductRequest item, ItemCallbacks callbacks, View view) {
        Call<Void> call = service.createProduct(item);

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

    public static void updateProduct(int id, ProductRequest item, ItemCallbacks callbacks, View view) {
        Call<Void> call = service.updateProduct(id, item);

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

    public static void getProduct(int id, ItemCallbacks callbacks) {
        Call<Product> call = service.getProduct(id);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    callbacks.afterItemLoad(response.body());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toasty.error(ApplicationWrapper.getAppContext(), t.getMessage(), Toast.LENGTH_SHORT, true).show();
            }

        });
    }
}
