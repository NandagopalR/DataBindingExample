package com.nanda.databindingexample.data.repo;

import com.nanda.databindingexample.data.api.AppApi;

import javax.inject.Inject;

public class AppRepo {

    private AppApi api;

    @Inject
    public AppRepo(AppApi api) {
        this.api = api;
    }

    public String getBooksList(String query) {
//        return api.getBooksList(query,ApiConstants.BOOKS_API_KEY)
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.)
        return "Hello";
    }

}
