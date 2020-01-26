package com.example.pricetag.data.model;

import com.example.pricetag.utils.ItemType;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Shop extends Item {

    private transient int userId;

    @SerializedName("address")
    private String address;

    @SerializedName("created_at")
    private Timestamp createdAt;

    @SerializedName("updated_at")
    private Timestamp updatedAt;

    @SerializedName("product_in_shops_attributes")
    private List<ProductInShop> productInShopAttributes = new ArrayList<>();

    public Shop(Integer id, String name, String address, int userId, Timestamp createdAt, Timestamp updatedAt) {
        super(id, name);
        this.address = address;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Shop(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    @Override
    public ItemType getType() { return ItemType.SHOP; }

    public void setName(String name) {
        super.setName(name);
    }

    public String getName() { return super.getName(); }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ProductInShop> getProductInShopAttributes() {
        return productInShopAttributes;
    }

    public void setProductInShopAttributes(List<ProductInShop> productInShopAttributes) {
        this.productInShopAttributes = productInShopAttributes;
    }

    @Override
    public String getEntity() {
        return "shops";
    }
}
