package com.nanda.databindingexample.data.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.nanda.databindingexample.data.db.livemodels.LiveRealmResults;
import com.nanda.databindingexample.data.factory.RealmConfigurationFactory;
import com.nanda.databindingexample.data.response.booklist.BooksModel;
import com.nanda.databindingexample.data.response.common.AppResponse;
import com.nanda.databindingexample.utils.RxJavaUtils;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class BookListViewModel extends BaseViewModel {

    @Inject
    public BookListViewModel() {
    }

    public String getData() {
        if (appRepo != null) {
            return "Hello, How are you?";
        }
        return "";
    }

    public MutableLiveData<AppResponse> getBookList(String query) {
        MutableLiveData<AppResponse> responseLiveData = new MutableLiveData<>();
        appRepo.getBooksList(query)
                .compose(RxJavaUtils.applyObserverSchedulers())
                .doOnSubscribe(() -> loadingStatus.setValue(true))
                .doAfterTerminate(() -> loadingStatus.setValue(false))
                .subscribe(booksModels -> {
                            responseLiveData.setValue(AppResponse.success(booksModels));
//                            response.setValue(AppResponse.success(booksModels));
                        },
                        throwable -> {
                            responseLiveData.setValue(AppResponse.error(throwable));
//                            response.setValue(AppResponse.error(throwable));
                        });
        return responseLiveData;
    }

    public LiveData<List<BooksModel>> getSavedBookList() {
        LiveData<List<BooksModel>> bookModelList;
        Realm realm = appRealm;
        bookModelList = new LiveRealmResults<>(realm.where(BooksModel.class).findAll());
        return bookModelList;
    }

    public void getTestBookList(String query) {
        appRepo.getBooksList(query)
                .compose(RxJavaUtils.applyObserverSchedulers())
                .doOnSubscribe(() -> loadingStatus.setValue(true))
                .doAfterTerminate(() -> loadingStatus.setValue(false))
                .subscribe(booksModels -> {
                            response.setValue(AppResponse.success(booksModels));
                        },
                        throwable -> {
                            response.setValue(AppResponse.error(throwable));
                        });

    }

    public void pushSubmitData() {

    }

}
