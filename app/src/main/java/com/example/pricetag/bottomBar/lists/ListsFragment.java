package com.example.pricetag.bottomBar.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.pricetag.R;
import com.example.pricetag.data.interfaces.ItemCallbacks;
import com.example.pricetag.data.model.Item;
import com.example.pricetag.data.repositories.list.ShoppingListRepository;
import com.example.pricetag.templates.index.IndexFragment;

import java.util.List;

public class ListsFragment extends IndexFragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState, this);
    }

    @Override
    public void loadData() {
        this.headingText = R.string.lists_heading;
        ShoppingListRepository.getShoppingLists(this);
    }



    @Override
    public void setItemData(List<? extends Item> data) {
        setRecyclerData(data);
    }
}