package com.example.pricetag.data.requests.user;

import com.google.gson.annotations.SerializedName;

public class SignupRequest extends LoginRequest{

    @SerializedName("password_confirmation")
    private String passwordConfirmation;

    public SignupRequest(String email, String password, String passwordConfirmation) {
        super(email, password);
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public boolean validate() {
        if (email.length() == 0) {
            return false;
        }

        if (password.length() == 0 || !password.equals(passwordConfirmation)) {
            return false;
        }

        return true;
    }
}
