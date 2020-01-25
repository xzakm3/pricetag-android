package com.example.pricetag.activities;

import android.app.Application;
import android.content.Context;

public class ApplicationWrapper extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ApplicationWrapper.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ApplicationWrapper.context;
    }

}
