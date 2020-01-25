package com.example.pricetag.data.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Product extends Item {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    private int userId;

    @SerializedName("created_at")
    private Timestamp createdAt;

    @SerializedName("updated_at")
    private Timestamp updateAt;

    public Product(int id, String name) {
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

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }
}
