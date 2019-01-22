package com.nanda.databindingexample.data.db.modules;

import com.nanda.databindingexample.data.response.booklist.BooksModel;
import com.nanda.databindingexample.data.response.booklist.ImageLinks;
import com.nanda.databindingexample.data.response.booklist.VolumeInfo;

import io.realm.annotations.RealmModule;

@RealmModule(library = true, classes = {BooksModel.class,VolumeInfo.class,ImageLinks.class})
public class BookModule {
}
