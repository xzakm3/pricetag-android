package com.example.pricetag.data.requests.user;

import android.app.Activity;

import com.example.pricetag.R;
import com.google.gson.annotations.SerializedName;

public class SignupRequest extends LoginRequest {

    @SerializedName("password_confirmation")
    private String passwordConfirmation;

    public SignupRequest(String email, String password, String passwordConfirmation, Activity context) {
        super(email, password, context);
        this.passwordConfirmation = passwordConfirmation;
        this.setValidationMessage(context.getString(R.string.signupFieldsError));
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public boolean validate() {
        if (getEmail().length() == 0) {
            return false;
        }

        if (getPassword().length() == 0 || !getPassword().equals(passwordConfirmation)) {
            return false;
        }

        return true;
    }
}
