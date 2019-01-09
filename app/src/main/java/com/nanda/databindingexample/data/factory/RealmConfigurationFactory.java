package com.nanda.databindingexample.data.factory;

import com.nanda.databindingexample.app.AppConstants;
import com.nanda.databindingexample.data.db.modules.PlantModule;

import javax.inject.Inject;

import io.realm.RealmConfiguration;

public class RealmConfigurationFactory {

    @Inject
    public RealmConfigurationFactory() {
    }

    public RealmConfiguration createRealmConfiguration() {
        return new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .modules(new PlantModule())
                .name(AppConstants.DATABASE_NAME)
                .build();
    }

}
//return RealmConfiguration.Builder()
//        .deleteRealmIfMigrationNeeded()
//        .modules(ParentAppModule())
//        .name(AppConstants.PARENT_DB_NAME)
//        .build()