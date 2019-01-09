package com.nanda.databindingexample.data.db.modules;

import com.nanda.databindingexample.data.db.models.PlantModel;

import io.realm.annotations.RealmModule;

@RealmModule(library = true, classes = {PlantModel.class})
public class PlantModule {
}