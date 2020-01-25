package com.example.pricetag.templates;

import com.example.pricetag.utils.ItemType;

public class ActionParameters {
    int itemId;
    ItemType itemType;
    String typeOfAction;

    public ActionParameters(int itemId, ItemType itemType, String typeOfAction) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.typeOfAction = typeOfAction;
    }

    public int getId() {
        return itemId;
    }

    public void setId(int itemId) {
        this.itemId = itemId;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public String getTypeOfAction() {
        return typeOfAction;
    }

    public void setTypeOfAction(String typeOfAction) {
        this.typeOfAction = typeOfAction;
    }

    public String getHeadingText() {
        return this.getTextForAction() + itemType.toString().toLowerCase();
    }

    public String getTextForAction() {
        if (this.typeOfAction.equals("create")) {
            return "Create new ";
        }
        return "Edit ";
    }
}
