package com.example.pricetag.data.requests;

import com.example.pricetag.data.model.Shop;
import com.google.gson.annotations.SerializedName;

public class ShopRequest extends BaseRequest {

    @SerializedName("shop")
    private Shop shop;

    public ShopRequest(Shop shop) {
        this.shop = shop;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public boolean validate() {
        if (shop.getAddress().equals("") || shop.getName().equals("")) {
            return false;
        }

        return true;
    }
}
