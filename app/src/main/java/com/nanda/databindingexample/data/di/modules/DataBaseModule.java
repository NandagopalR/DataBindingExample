package com.nanda.databindingexample.data.di.modules;

import com.nanda.databindingexample.data.factory.RealmConfigurationFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;

@Module
public class DataBaseModule {

    @Provides
    @Singleton
    RealmConfiguration providesRealmConfiguration() {
        return RealmConfigurationFactory.createRealmConfiguration();
    }

}
