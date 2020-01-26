package com.example.pricetag.templates.action;

import android.view.View;

import com.example.pricetag.utils.ItemType;

public class ItemActionTemplate {
    private String headingText;
    private String nameTextViewContent;
    private ItemType itemType;

    public ItemActionTemplate(String headingText, String nameTextViewContent, ItemType itemType) {
        this.headingText = headingText;
        this.nameTextViewContent = nameTextViewContent;
        this.itemType = itemType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public int getVisibility() {
        if(itemType.equals(ItemType.SHOP)) {
            return View.VISIBLE;
        }
        return View.GONE;
    }

    public String getHeadingText() {
        return headingText;
    }

    public void setHeadingText(String headingText) {
        this.headingText = headingText;
    }

    public String getNameTextViewContent() {
        return nameTextViewContent;
    }

    public void setNameTextViewContent(String nameTextViewContent) {
        this.nameTextViewContent = nameTextViewContent;
    }

    public String getItemToChooseContent() {
        if(itemType.equals(ItemType.SHOP) || itemType.equals(ItemType.SHOPPINGLIST)) {
            return "Products";
        }
        return "Shops";
    }

    public String getNumberEditTextContent() {
        if(itemType.equals(ItemType.SHOP) || itemType.equals(ItemType.PRODUCT)) {
            return "put price";
        }
        return "quantity";
    }
}
