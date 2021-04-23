package com.rdmns24.chamiapps.rdmns24live;

import android.app.Application;
import android.content.Context;

import com.facebook.ads.AudienceNetworkAds;

public class App extends Application {

    private static Context appContext;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        AudienceNetworkAds.initialize(appContext);
    }


    public static Context getContext() {
        return appContext;
    }

    public static void logException(Exception e) {
        e.printStackTrace();
    }
}
