package com.nanda.databindingexample.data.viewmodels;

import com.nanda.databindingexample.data.response.common.AppResponse;
import com.nanda.databindingexample.utils.RxJavaUtils;

import javax.inject.Inject;

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

    public void getBookList(String query) {
        appRepo.getBooksList(query)
                .compose(RxJavaUtils.applyObserverSchedulers())
                .doOnSubscribe(disposable -> loadingStatus.setValue(true))
                .doAfterTerminate(() -> loadingStatus.setValue(false))
                .subscribe(booksModels -> {
                            response.setValue(AppResponse.success(booksModels));
                        },
                        throwable -> {
                            response.setValue(AppResponse.error(throwable));
                        });

    }

    public void getTestBookList(String query) {
        appRepo.getBooksList(query)
                .compose(RxJavaUtils.applyObserverSchedulers())
                .doOnSubscribe(disposable -> loadingStatus.setValue(true))
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
