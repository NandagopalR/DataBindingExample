package com.nanda.databindingexample.data.viewmodels;

import android.arch.lifecycle.LiveData;

import com.nanda.databindingexample.data.db.livemodels.LiveRealmResults;
import com.nanda.databindingexample.data.response.booklist.BooksModel;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class SavedBooksViewModel extends BaseViewModel {

    @Inject
    public SavedBooksViewModel() {
    }

    public LiveData<List<BooksModel>> getSavedBookList() {
        LiveData<List<BooksModel>> bookModelList;
        Realm realm = appRealm;
        bookModelList = new LiveRealmResults<>(realm.where(BooksModel.class).findAll());
        return bookModelList;
    }

}
