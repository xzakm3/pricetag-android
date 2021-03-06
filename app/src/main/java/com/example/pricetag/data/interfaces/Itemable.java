package com.example.pricetag.data.interfaces;

import com.example.pricetag.utils.ItemType;

public interface Itemable {
    Integer getId();
    ItemType getType();
    String getName();
    String getEntity();
    float getNumber();
}
