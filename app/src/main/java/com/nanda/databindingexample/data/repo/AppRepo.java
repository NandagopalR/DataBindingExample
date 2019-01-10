package com.nanda.databindingexample.data.repo;

import com.nanda.databindingexample.app.AppConstants;
import com.nanda.databindingexample.data.api.ApiConstants;
import com.nanda.databindingexample.data.api.AppApi;
import com.nanda.databindingexample.data.factory.RealmConfigurationFactory;
import com.nanda.databindingexample.data.response.booklist.BooksModel;
import com.nanda.databindingexample.utils.RxJavaUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

public class AppRepo {

    private AppApi api;
    private Realm realm;

    @Inject
    RealmConfigurationFactory realmConfigFactory;

    @Inject
    public AppRepo(AppApi api) {
        this.api = api;
        createRealm();
    }

    private void createRealm() {
        realm = Realm.getInstance(realmConfigFactory.createRealmConfiguration());
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

}
