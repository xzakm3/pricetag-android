package com.example.pricetag.data.model;

import com.example.pricetag.utils.ItemType;

import java.sql.Timestamp;

public class ShoppingList extends Item {
    private int id;
    private String name;
    private int userId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ShoppingList(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public ItemType getType() { return ItemType.SHOPPINGLIST; }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
