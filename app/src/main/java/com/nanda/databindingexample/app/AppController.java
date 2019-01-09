package com.nanda.databindingexample.app;

import com.nanda.databindingexample.data.di.componants.DaggerAppComponant;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import io.realm.Realm;

public class AppController extends DaggerApplication {

    private static AppController appController;

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
        Realm.init(this);
    }

    public static AppController getInstance() {
        return appController;
    }

    @Override
    protected AndroidInjector<? extends AppController> applicationInjector() {
        return DaggerAppComponant.builder().create(this);
    }
}
