package com.example.pricetag.templates.basket;

import android.view.View;

public class ItemsForBasketTemplate {
    private String headingTextViewContent;
    private String url;
    private int visibilityOfQuantityField;

    public ItemsForBasketTemplate(String url) {
        this.url = url;
        setTemplate();
    }

    private void setTemplate() {
        if (this.url.equals("products")) {
            headingTextViewContent = "Products";
            visibilityOfQuantityField = View.VISIBLE;
        } else {
            headingTextViewContent = "Shopping lists";
            visibilityOfQuantityField = View.INVISIBLE;
        }
    }

    public String getHeadingTextViewContent() {
        return this.headingTextViewContent;
    }

    public int getVisibility() {
        return this.visibilityOfQuantityField;
    }
}
