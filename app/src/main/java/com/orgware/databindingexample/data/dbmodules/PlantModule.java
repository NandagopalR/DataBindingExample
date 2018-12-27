package com.orgware.databindingexample.data.dbmodules;

import com.orgware.databindingexample.data.dbmodels.PlantModel;

import io.realm.annotations.RealmModule;

@RealmModule(library = false, classes = {PlantModel.class})
public class PlantModule {
}
//@RealmModule(library = false, classes = arrayOf(UserModel::class))
//class ParentAppModule {
//}