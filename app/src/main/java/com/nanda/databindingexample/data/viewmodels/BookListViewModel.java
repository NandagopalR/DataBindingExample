package com.nanda.databindingexample.data.viewmodels;

import com.nanda.databindingexample.data.repo.AppRepo;
import com.nanda.databindingexample.utils.RxJavaUtils;

import javax.inject.Inject;

public class BookListViewModel extends BaseViewModel {

    @Inject
    BookListViewModel(AppRepo appRepo) {
        this.appRepo = appRepo;
    }

    public String getData() {
        if (appRepo != null) {
            return "Hello, How are you?";
        }
        return "";
    }

    public void getBookList(String query) {
        appRepo.getBooksList(query)
                .compose(RxJavaUtils.applyObserverSchedulers());

    }
}
