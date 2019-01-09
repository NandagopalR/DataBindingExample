package com.nanda.databindingexample.data.di.componants;

import com.nanda.databindingexample.app.AppController;
import com.nanda.databindingexample.data.di.modules.ActivityBuildersModule;
import com.nanda.databindingexample.data.di.modules.AppModule;
import com.nanda.databindingexample.data.di.modules.DataBaseModule;
import com.nanda.databindingexample.data.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuildersModule.class,
        AppModule.class,
        NetworkModule.class, DataBaseModule.class})
public interface AppComponant extends AndroidInjector<AppController> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<AppController> {
    }
}
