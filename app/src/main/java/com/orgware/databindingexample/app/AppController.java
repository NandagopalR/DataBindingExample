package com.orgware.databindingexample.app;

import android.app.Application;

import com.orgware.databindingexample.data.factory.RealmConfigurationFactory;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppController extends Application {

    private static AppController appController;

    private RealmConfiguration realmConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
        Realm.init(this);
    }

    public static AppController getInstance() {
        return appController;
    }

    public RealmConfiguration getRealmConfiguration() {
        if (realmConfiguration == null) {
            return realmConfiguration = RealmConfigurationFactory.createRealmConfiguration();
        } else return realmConfiguration;
    }

}
