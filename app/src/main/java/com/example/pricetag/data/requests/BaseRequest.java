package com.example.pricetag.data.requests;

import android.app.Activity;
import android.widget.Toast;

import com.example.pricetag.activities.ApplicationWrapper;

import es.dmoral.toasty.Toasty;

abstract public class BaseRequest {

    private transient String validationMessage = "Please fill in all the fields!";

    private transient Activity context;

    public Activity getContext() {
        return context;
    }

    protected void setContext(Activity context) {
        this.context = context;
    }

    private String getValidationMessage() {
        return validationMessage;
    }

    protected void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }


    public boolean validate() {
        return true;
    }


    public boolean validateRequest() {
        if (!validate()) {
            Toasty.error(ApplicationWrapper.getAppContext(), getValidationMessage(), Toast.LENGTH_SHORT, true).show();
            return false;
        }

        return true;
    }


}
