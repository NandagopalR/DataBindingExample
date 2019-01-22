package com.nanda.databindingexample.data.viewmodels;

import android.arch.lifecycle.MutableLiveData;

import com.nanda.databindingexample.data.response.common.AppResponse;
import com.nanda.databindingexample.utils.RxJavaUtils;

import javax.inject.Inject;

public class BookListViewModel extends BaseViewModel {

    @Inject
    public BookListViewModel() {
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

}
