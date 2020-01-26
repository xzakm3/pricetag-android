package com.example.pricetag.utils;

import android.content.Context;
import android.content.SharedPreferences;


import com.example.pricetag.activities.ApplicationWrapper;
import com.example.pricetag.data.interfaces.ItemToCalculatable;
import com.example.pricetag.data.model.ItemToCalculate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Preferences {
    public static void setAccessToken(String token) {
        Context context = ApplicationWrapper.getAppContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("ACCESSTOKEN", token);
        editor.apply();
    }

    public static String getAccessToken() {
        Context context = ApplicationWrapper.getAppContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("ACCESSTOKEN", null);
    }

    public static void removeAccessToken() {
        Context context = ApplicationWrapper.getAppContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove("ACCESSTOKEN");
        editor.apply();
    }

    public static void addItemsForBasket(List<ItemToCalculatable> newBasketItems) {
        List<ItemToCalculatable> basketItemsToSave = newBasketItems;

        Context context = ApplicationWrapper.getAppContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        List<ItemToCalculatable> existingbasketIetms = getBasketItems();
        if(existingbasketIetms != null) {
            basketItemsToSave = updateExistingItems(existingbasketIetms, newBasketItems);
        }

        Gson gson = new Gson();
        String json = gson.toJson(basketItemsToSave);

        editor.putString("SHOPPINGBASKET", json);
        editor.commit();
    }

    public static void setItemsForBasket(List<ItemToCalculatable> basketItems) {

        Context context = ApplicationWrapper.getAppContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(basketItems);

        editor.putString("SHOPPINGBASKET", json);
        editor.commit();
    }

    public static List<ItemToCalculatable> getBasketItems() {
        Context context = ApplicationWrapper.getAppContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("SHOPPINGBASKET", "");

        Type type = new TypeToken<List<ItemToCalculate>>(){}.getType();
        List<ItemToCalculatable> basketItems = gson.fromJson(json, type);

        if (basketItems == null) {
            return new ArrayList<>();
        }
        return basketItems;
    }

    public static void removeItemFromBasket(ItemToCalculatable item) {
        List<ItemToCalculatable> basketItems = getBasketItems();
        basketItems = removeItemFromListById(basketItems, item.getId());
        setItemsForBasket(basketItems);
    }

    public static void removeBasket() {
        Context context = ApplicationWrapper.getAppContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("SHOPPINGBASKET").commit();
    }

    private static List<ItemToCalculatable> removeItemFromListById(List<ItemToCalculatable> items, int itemId) {
        for (int i = 0; i < items.size(); i++) {
            ItemToCalculatable item = items.get(i);
            if(item.getId() == itemId) {
                items.remove(item);
                break;
            }
        }
        return items;
    }

    private static List<ItemToCalculatable> updateExistingItems(List<ItemToCalculatable> existingbasketIetms, List<ItemToCalculatable> newBasketItems) {
        for (ItemToCalculatable newItem: newBasketItems) {
            boolean foundExistingItem = false;
            for (ItemToCalculatable existingItem: existingbasketIetms) {
                if(newItem.getId() == existingItem.getId()) {
                    int newQuantity = existingItem.getQuantity() + newItem.getQuantity();
                    existingItem.setQuantity(newQuantity);
                    foundExistingItem = true;
                    break;
                }
            }
            if(!foundExistingItem) {
                existingbasketIetms.add(newItem);
            }
        }
        return existingbasketIetms;
    }
}
