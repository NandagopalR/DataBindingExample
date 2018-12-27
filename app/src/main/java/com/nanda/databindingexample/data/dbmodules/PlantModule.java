package com.nanda.databindingexample.data.dbmodules;

import com.nanda.databindingexample.data.dbmodels.PlantModel;

import io.realm.annotations.RealmModule;

@RealmModule(library = true, classes = {PlantModel.class})
public class PlantModule {
}