package com.example.pricetag.data.interfaces;

import com.example.pricetag.data.model.Item;

import java.util.List;

public interface ItemCallbacks {
    void setItemData(List<? extends Item> data);
}
