package com.example.pricetag.bottomBar.shops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.pricetag.IndexFragment;
import com.example.pricetag.R;
import com.example.pricetag.data.model.Shop;

import java.util.Arrays;
import java.util.List;

public class ShopsFragment extends IndexFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState, this);
    }

    @Override
    public void loadData() {
        this.data = generateData();
        this.headingText = R.string.shops_heading;
    }

    private List<Shop> generateData() {
        List<Shop> d = Arrays.asList(
                new Shop(1, "LIDL", "French address 1"),
                new Shop(2, "Super U", "French address 2"),
                new Shop(1, "Carrefour", "French address 3")
        );
        return d;
    }
}