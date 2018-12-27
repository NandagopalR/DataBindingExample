package com.nanda.databindingexample.data.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.nanda.databindingexample.data.di.keys.ViewModelKey;
import com.nanda.databindingexample.data.factory.ViewModelFactory;
import com.nanda.databindingexample.data.viewmodels.ListBooksModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListBooksModel.class)
    ViewModel bindListBooksModel(ListBooksModel booksModel);

    @Binds
    ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
