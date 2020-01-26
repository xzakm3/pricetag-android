package com.example.pricetag.data.model;

public class ProductInShopBuilder {

    public Integer id;

    public Integer productId;

    public Integer shopId;

    public float price;

    public Integer destroy;

    public String shopName;
    public String productName;

    public ProductInShopBuilder setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public ProductInShopBuilder setShopId(Integer shopId) {
        this.shopId = shopId;
        return this;
    }

    public ProductInShopBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public ProductInShopBuilder setDestroy(Integer destroy) {
        this.destroy = destroy;
        return this;
    }

    public ProductInShopBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ProductInShopBuilder setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public ProductInShopBuilder setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public ProductInShop build() {

        return new ProductInShop(this);
    }
}
