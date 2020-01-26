package com.example.pricetag.data.model;

import com.google.gson.annotations.SerializedName;

public class CalculateProduct {
    @SerializedName("id")
    private int id;

    @SerializedName("quantity")
    private int quantity;

    public CalculateProduct(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
