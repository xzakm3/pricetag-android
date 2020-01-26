package com.example.pricetag.data.model;

import com.example.pricetag.templates.ActionParameters;
import com.example.pricetag.utils.ItemType;
import com.google.gson.annotations.SerializedName;

public class ProductInShop implements ActionFragmentItemable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("product_id")
    private Integer productId;

    @SerializedName("shop_id")
    private Integer shopId;

    @SerializedName("price")
    private float price;

    @SerializedName("_destroy")
    private Integer destroy;

    @SerializedName("shop")
    private Shop shop;

    @SerializedName("product")
    private Product product;

    private transient String productName;

    private transient String shopName;


    ProductInShop(ProductInShopBuilder builder) {
        this.id = builder.id;
        this.productId = builder.productId;
        this.shopId = builder.shopId;
        this.price = builder.price;
        this.destroy = builder.destroy;
        this.productName = builder.productName;
        this.shopName = builder.shopName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getDestroy() {
        return destroy;
    }

    public void setDestroy(Integer destroy) {
        this.destroy = destroy;
    }

    @Override
    public String getName() {
        if (shopId != null) {
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

    public void syncShop() {
        shopName = shop.getName();
        shopId = shop.getId();
    }

    public void syncProduct() {
        productName = product.getName();
        productId = product.getId();
    }
}
