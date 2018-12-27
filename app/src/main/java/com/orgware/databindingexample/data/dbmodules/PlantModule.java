package com.orgware.databindingexample.data.dbmodules;

import com.orgware.databindingexample.data.dbmodels.PlantModel;

import io.realm.annotations.RealmModule;

@RealmModule(library = true, classes = {PlantModel.class})
public class PlantModule {
}