package com.nanda.databindingexample.data.repo;

import android.arch.lifecycle.LiveData;

import com.nanda.databindingexample.app.AppConstants;
import com.nanda.databindingexample.data.api.ApiConstants;
import com.nanda.databindingexample.data.api.AppApi;
import com.nanda.databindingexample.data.db.livemodels.LiveRealmResults;
import com.nanda.databindingexample.data.response.booklist.BooksModel;
import com.nanda.databindingexample.utils.RxJavaUtils;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.Observable;
import rx.functions.Action1;

public class AppRepo {

    private AppApi api;
    private RealmConfiguration realmConfiguration;
    private Realm appRealm;

    @Inject
    public AppRepo(AppApi api, RealmConfiguration realmConfiguration) {
        this.api = api;
        this.realmConfiguration = realmConfiguration;
        createRealm();
    }

    private void createRealm() {
        appRealm = Realm.getInstance(realmConfiguration);
    }

    public Observable<List<BooksModel>> getBooksList(String query) {
        return api.getBooksList(query, ApiConstants.BOOKS_API_KEY)
                .compose(RxJavaUtils.applyErrorTransformer())
                .map(response -> {
                    if (response == null || response.getBooksModel() == null || response.getBooksModel().size() == 0) {
                        throw new RuntimeException(AppConstants.API_UNKNOWN_FAILURE_MSG);
                    }
                    return response.getBooksModel();
                });
    }

    /*------------------------------------------DataBase-----------------------------------------------------*/

    private LiveData<List<BooksModel>> getSavedBookList() {
        LiveData<List<BooksModel>> bookModelList;
        Realm realm = appRealm;
        bookModelList = new LiveRealmResults<BooksModel>(realm.where(BooksModel.class).findAll());
        return bookModelList;
    }

    private class SaveBookModelList implements Action1<List<BooksModel>> {

        @Override
        public void call(List<BooksModel> booksModelList) {

        }
    }

}
