package com.example.pricetag.data.interfaces;

import android.view.View;

import com.example.pricetag.data.model.Item;

import java.util.List;

public interface ItemCallbacks {
    void setItemData(List<? extends Item> data);
    void afterCreate(View view);
    void afterItemLoad(Itemable item);
    void afterUpdate(View view);
}
