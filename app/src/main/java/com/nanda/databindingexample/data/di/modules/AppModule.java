package com.nanda.databindingexample.data.di.modules;

import com.nanda.databindingexample.data.api.AppApi;
import com.nanda.databindingexample.data.repo.AppRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    AppApi provideApi(Retrofit retrofit) {
        return retrofit.create(AppApi.class);
    }

    @Provides
    @Singleton
    AppRepo providesAppRepo(AppApi appApi) {
        return new AppRepo(appApi);
    }

}
