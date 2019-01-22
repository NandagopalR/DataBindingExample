package com.nanda.databindingexample.data.di.modules;

import com.nanda.databindingexample.ui.home.HomeActivity;
import com.nanda.databindingexample.ui.plantlist.BookListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    HomeActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    BookListActivity contributeBookListActivity();

}
