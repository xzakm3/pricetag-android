package com.example.pricetag.data.requests.user;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("email")
    protected String email;

    @SerializedName("password")
    protected String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


