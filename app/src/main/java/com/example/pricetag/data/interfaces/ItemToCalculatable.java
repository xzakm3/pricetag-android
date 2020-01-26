package com.example.pricetag.data.interfaces;

public interface ItemToCalculatable {
    int getId();
    String getName();
    int getQuantity();
    void setQuantity(int quantity);
    boolean isChecked();
    void setChecked(boolean isChecked);
}
