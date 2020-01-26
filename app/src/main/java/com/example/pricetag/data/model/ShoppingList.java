package com.example.pricetag.data.model;

import com.example.pricetag.utils.ItemType;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList extends Item {

    private transient int userId;

    @SerializedName("created_at")
    private Timestamp createdAt;

    @SerializedName("updated_at")
    private Timestamp updatedAt;


    @SerializedName(value="product_in_shopping_lists_attributes", alternate={"product_in_shopping_lists"})
    private List<ProductInShoppingList> productInShoppingLists = new ArrayList<>();

    @Override
    public ItemType getType() { return ItemType.SHOPPINGLIST; }

    @Override
    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public ShoppingList(Integer id, String name) {
        super(id, name);
    }

    public ShoppingList(int id, String name, int userId, Timestamp createdAt, Timestamp updatedAt) {
        super(id, name);
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public List<ProductInShoppingList> getProductInShoppingLists() {
        return productInShoppingLists;
    }

    public void setProductInShoppingLists(List<ProductInShoppingList> productInShoppingLists) {
        this.productInShoppingLists = productInShoppingLists;
    }

    public Integer getUserId() {
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

    @Override
    public String getEntity() {
        return "shopping_lists";
    }
}
