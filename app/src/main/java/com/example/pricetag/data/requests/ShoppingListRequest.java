package com.example.pricetag.data.requests;

import com.example.pricetag.data.model.ShoppingList;
import com.google.gson.annotations.SerializedName;

public class ShoppingListRequest extends BaseRequest {

    @SerializedName("shopping_list")
    private ShoppingList shoppingList;

    public ShoppingListRequest(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    @Override
    public boolean validate() {

        if (shoppingList.getName() == null || shoppingList.getName().equals("")) {
            return false;
        }

        return true;
    }
}
