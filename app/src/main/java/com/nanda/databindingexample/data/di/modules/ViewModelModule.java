package com.nanda.databindingexample.data.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.nanda.databindingexample.data.di.keys.ViewModelKey;
import com.nanda.databindingexample.data.factory.ViewModelFactory;
import com.nanda.databindingexample.data.viewmodels.BookListViewModel;
import com.nanda.databindingexample.data.viewmodels.SavedBooksViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SavedBooksViewModel.class)
    ViewModel bindSavedBooksModel(SavedBooksViewModel savedBooksViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BookListViewModel.class)
    ViewModel bindListBooksModel(BookListViewModel bookListViewModel);

    @Binds
    ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
