package com.example.pricetag.data.model;

public class ProductInShopBuilder {

    public int id;

    public int productId;

    public int shopId;

    public float price;

    public int destroy;

    public String shopName;
    public String productName;

    public ProductInShopBuilder setProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public ProductInShopBuilder setShopId(int shopId) {
        this.shopId = shopId;
        return this;
    }

    public ProductInShopBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public ProductInShopBuilder setDestroy(int destroy) {
        this.destroy = destroy;
        return this;
    }

    public ProductInShopBuilder setId(int id) {
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
