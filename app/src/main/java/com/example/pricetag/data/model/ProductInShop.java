package com.example.pricetag.data.model;

import com.example.pricetag.templates.ActionParameters;
import com.example.pricetag.utils.ItemType;
import com.google.gson.annotations.SerializedName;

public class ProductInShop implements ActionFragmentItemable {

    @SerializedName("id")
    private int id;

    @SerializedName("product_id")
    private int productId;

    @SerializedName("shop_id")
    private int shopId;

    @SerializedName("price")
    private float price;

    @SerializedName("_destroy")
    private int destroy;

    private String productName;

    private String shopName;


    public ProductInShop(ProductInShopBuilder builder) {
        this.id = builder.id;
        this.productId = builder.productId;
        this.shopId = builder.shopId;
        this.price = builder.price;
        this.destroy = builder.destroy;
        this.productName = builder.productName;
        this.shopName = builder.shopName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDestroy() {
        return destroy;
    }

    public void setDestroy(int destroy) {
        this.destroy = destroy;
    }

    @Override
    public String getName() {
        if (shopId > 0) {
            return shopName;
        }

        return productName;
    }

    @Override
    public float getNumber() {
        return price;
    }

    public int getDependant(ActionParameters params) {
        if (params.getItemType() == ItemType.PRODUCT) {
            return getShopId();
        } else if (params.getItemType() == ItemType.SHOP){
            return getProductId();
        }

        return getProductId();
    }
}
