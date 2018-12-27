package com.orgware.databindingexample.app;

import android.app.Application;

public class AppController extends Application {

    private static AppController appController;

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
    }

    public static AppController getInstance() {
        return appController;
    }

}
