package com.nanda.databindingexample.data.di.modules;

import android.content.Context;

import com.nanda.databindingexample.app.AppController;
import com.nanda.databindingexample.data.api.AppApi;
import com.nanda.databindingexample.data.factory.RealmConfigurationFactory;
import com.nanda.databindingexample.data.repo.AppRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    Context providesContext() {
        return AppController.getInstance().getApplicationContext();
    }

    @Provides
    @Singleton
    AppApi provideApi(Retrofit retrofit) {
        return retrofit.create(AppApi.class);
    }

    @Provides
    @Singleton
    RealmConfiguration providesRealmConfiguration() {
        return new RealmConfigurationFactory().createRealmConfiguration();
    }

    @Provides
    @Singleton
    AppRepo providesAppRepo(AppApi appApi) {
        return new AppRepo(appApi);
    }

}
