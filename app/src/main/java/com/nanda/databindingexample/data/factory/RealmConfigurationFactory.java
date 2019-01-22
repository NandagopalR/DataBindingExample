package com.nanda.databindingexample.data.factory;

import com.nanda.databindingexample.app.AppConstants;
import com.nanda.databindingexample.data.db.modules.BookModule;

import io.realm.RealmConfiguration;

public class RealmConfigurationFactory {

//    public static RealmConfiguration createRealmConfiguration() {
//        return new RealmConfiguration
//                .Builder()
//                .deleteRealmIfMigrationNeeded()
//                .modules(new PlantModule())
//                .name(AppConstants.DATABASE_NAME)
//                .build();
//    }

    public static RealmConfiguration createRealmConfiguration() {
        return new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .modules(new BookModule())
                .name(AppConstants.DATABASE_NAME)
                .build();
    }

}
//return RealmConfiguration.Builder()
//        .deleteRealmIfMigrationNeeded()
//        .modules(ParentAppModule())
//        .name(AppConstants.PARENT_DB_NAME)
//        .build()