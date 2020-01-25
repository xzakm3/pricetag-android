package com.example.pricetag.data.model;

import com.example.pricetag.utils.ItemType;

import java.sql.Timestamp;

public class ShoppingList extends Item {
    private int userId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Override
    public ItemType getType() { return ItemType.SHOPPINGLIST; }

    @Override
    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public ShoppingList(int id, String name) {
        super(id, name);
    }

    public ShoppingList(int id, String name, int userId, Timestamp createdAt, Timestamp updatedAt) {
        super(id, name);
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    @Override
    public String getEntity() {
        return "shopping_lists";
    }
}
