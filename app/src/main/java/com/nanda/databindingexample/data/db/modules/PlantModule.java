package com.nanda.databindingexample.data.db.modules;

import com.nanda.databindingexample.data.db.models.PlantModel;
import com.nanda.databindingexample.data.response.booklist.BooksModel;

import io.realm.annotations.RealmModule;

@RealmModule(library = true, classes = {PlantModel.class,BooksModel.class})
public class PlantModule {
}