package com.example.pricetag.data.requests.user;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("auth_token")
    private String token;

    @SerializedName("message")
    private String message;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}