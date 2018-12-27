package com.nanda.databindingexample.app;

import com.nanda.databindingexample.data.di.componants.DaggerAppComponant;
import com.nanda.databindingexample.data.factory.RealmConfigurationFactory;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppController extends DaggerApplication {

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

    @Override
    protected AndroidInjector<? extends AppController> applicationInjector() {
        return DaggerAppComponant.builder().create(this);
    }
}
