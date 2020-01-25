package com.example.pricetag.data.model;

import java.sql.Timestamp;

public class Shop extends Item {
    private String address;
    private int userId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Shop(int id, String name, String address, int userId, Timestamp createdAt, Timestamp updatedAt) {
        super(id, name);
        this.address = address;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Shop(int id, String name, String address) {
        super(id, name);
        this.address = address;
    }


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

    @Override
    public String getEntity() {
        return "shops";
    }
}
