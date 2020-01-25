package com.example.pricetag.data.model;

public class ActionFragmentItem {
    private String name;
    private int number;

    public ActionFragmentItem(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setText(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
