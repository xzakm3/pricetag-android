package com.example.pricetag.bottomBar.shops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.pricetag.R;
import com.example.pricetag.data.interfaces.ItemCallbacks;
import com.example.pricetag.data.model.Item;
import com.example.pricetag.data.model.Shop;
import com.example.pricetag.data.repositories.shop.ShopRepository;
import com.example.pricetag.templates.index.IndexAdapter;
import com.example.pricetag.templates.index.IndexFragment;

import java.util.ArrayList;
import java.util.List;

public class ShopsFragment extends IndexFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState, this);
    }

    @Override
    public void loadData() {
        this.headingText = R.string.shops_heading;

        ShopRepository.getShops(this);
    }

}