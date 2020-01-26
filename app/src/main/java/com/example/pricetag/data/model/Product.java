package com.example.pricetag.data.model;

import com.example.pricetag.utils.ItemType;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Product extends Item {

    private transient Integer userId;

    @SerializedName("created_at")
    private Timestamp createdAt;

    @SerializedName("updated_at")
    private Timestamp updateAt;

    @SerializedName("product_in_shops_attributes")
    private List<ProductInShop> productInShopAttributes = new ArrayList<>();

    public Product(Integer id, String name) {
        super(id, name);
    }

    public Product(Integer id, String name, List<ProductInShop> productInShopAttributes) {
        super(id, name);
        this.productInShopAttributes = productInShopAttributes;
    }

    public ItemType getType() { return ItemType.PRODUCT; }

    public Product(Integer id, String name, int userId, Timestamp createdAt, Timestamp updateAt) {
        super(id, name);
        this.userId = userId;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public List<ProductInShop> getProductInShopAttributes() {
        return productInShopAttributes;
    }

    public void setProductInShopAttributes(List<ProductInShop> productInShopAttributes) {
        this.productInShopAttributes = productInShopAttributes;
    }

    @Override
    public String getEntity() {
        return "products";
    }

    @Override
    public float getNumber() {
        return 0;
    }
}
