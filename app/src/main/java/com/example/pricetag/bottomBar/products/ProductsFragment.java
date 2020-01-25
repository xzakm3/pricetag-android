package com.example.pricetag.bottomBar.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.pricetag.data.interfaces.ItemCallbacks;
import com.example.pricetag.data.model.Item;
import com.example.pricetag.data.repositories.product.ProductRepository;
import com.example.pricetag.templates.index.IndexAdapter;
import com.example.pricetag.templates.index.IndexFragment;
import com.example.pricetag.R;
import com.example.pricetag.data.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends IndexFragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState, this);
    }

    @Override
    public void loadData() {
        this.headingText = R.string.products_heading;

        ProductRepository.getProducts(this);
    }

    @Override
    public void setItemData(List<? extends Item> data) {
        setRecyclerData(data);
    }

}