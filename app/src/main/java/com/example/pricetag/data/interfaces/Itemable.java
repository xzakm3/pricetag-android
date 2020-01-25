package com.example.pricetag.data.interfaces;

import com.example.pricetag.utils.ItemType;

public interface Itemable {
    String getName();

    int getId();

    ItemType getType();
}
