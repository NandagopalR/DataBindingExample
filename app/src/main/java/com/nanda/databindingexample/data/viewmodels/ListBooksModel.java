package com.nanda.databindingexample.data.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.nanda.databindingexample.data.repo.AppRepo;

import javax.inject.Inject;

public class ListBooksModel extends ViewModel {

    private AppRepo appRepo;

    @Inject
    public ListBooksModel(AppRepo appRepo) {
        this.appRepo = appRepo;
    }

    public String getData() {
        return appRepo.getBooksList("gfs");
    }
}
