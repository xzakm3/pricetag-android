package com.example.pricetag.data.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Product extends Item {

    private int userId;

    @SerializedName("created_at")
    private Timestamp createdAt;

    @SerializedName("updated_at")
    private Timestamp updateAt;

    public Product(int id, String name) {
        super(id, name);
    }

    public Product(int id, String name, int userId, Timestamp createdAt, Timestamp updateAt) {
        super(id, name);
        this.userId = userId;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
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

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String getEntity() {
        return "products";
    }
}
