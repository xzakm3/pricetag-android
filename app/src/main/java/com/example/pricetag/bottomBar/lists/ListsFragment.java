package com.example.pricetag.bottomBar.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.pricetag.data.interfaces.Itemable;
import com.example.pricetag.templates.index.IndexFragment;
import com.example.pricetag.R;
import com.example.pricetag.data.model.ShoppingList;

import java.util.Arrays;
import java.util.List;

public class ListsFragment extends IndexFragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState, this);
    }

    @Override
    public void loadData() {
        this.data = generateData();
        this.headingText = R.string.lists_heading;
    }

    private List<Itemable> generateData() {
        List<Itemable> d = Arrays.asList(
                new ShoppingList(1, "Sunday's specialty"),
                new ShoppingList(2, "Thursday shopping"),
                new ShoppingList(3, "Pancakes")
        );
        return d;
    }
}