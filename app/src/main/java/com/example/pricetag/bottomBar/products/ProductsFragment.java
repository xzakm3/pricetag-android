package com.example.pricetag.bottomBar.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.pricetag.IndexFragment;
import com.example.pricetag.R;
import com.example.pricetag.data.model.Product;

import java.util.Arrays;
import java.util.List;

public class ProductsFragment extends IndexFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState, this);
    }

    @Override
    public void loadData() {
        this.data = generateData();
        this.headingText = R.string.products_heading;
    }

    private List<Product> generateData() {
        List<Product> d = Arrays.asList(
                new Product(1, "Orange"),
                new Product(2, "Lemon"),
                new Product(3, "Potato"),
                new Product(4, "Pepper"),
                new Product(5, "Cucumber")
        );
        return d;
    }
}