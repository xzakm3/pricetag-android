package com.example.pricetag.data.requests.user;

import android.app.Activity;

import com.example.pricetag.R;
import com.example.pricetag.data.requests.BaseRequest;
import com.google.gson.annotations.SerializedName;

public class LoginRequest extends BaseRequest {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;


    public LoginRequest(String email, String password, Activity context) {
        this.email = email;
        this.password = password;
        this.setContext(context);
        this.setValidationMessage(context.getString(R.string.loginFieldsError));
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


    public boolean validate() {

        if (email == null || password == null || email.equals("") || password.equals("")) {
            return false;
        }

        return true;
    }

}


