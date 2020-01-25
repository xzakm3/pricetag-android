package com.example.pricetag.data.model;

import com.example.pricetag.data.interfaces.Itemable;
import com.example.pricetag.utils.ItemType;
import com.google.gson.annotations.SerializedName;

public class Item implements Itemable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEntity() {
        return null;
    }

    @Override
    public ItemType getType() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }
}
