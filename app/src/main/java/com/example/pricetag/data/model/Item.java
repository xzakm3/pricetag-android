package com.example.pricetag.data.model;

import com.example.pricetag.data.interfaces.Itemable;
import com.example.pricetag.utils.ItemType;

public class Item implements Itemable {
    @Override
    public String getName() {
        return null;
    }

    public int getId() { return 0; }

    @Override
    public ItemType getType() {
        return null;
    }
}
