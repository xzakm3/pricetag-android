package com.example.pricetag.utils;

import android.content.Context;
import android.content.SharedPreferences;


import com.example.pricetag.activities.ApplicationWrapper;

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
}