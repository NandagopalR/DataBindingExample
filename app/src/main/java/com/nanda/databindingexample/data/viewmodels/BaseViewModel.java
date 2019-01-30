package com.nanda.databindingexample.data.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.nanda.databindingexample.data.factory.RealmConfigurationFactory;
import com.nanda.databindingexample.data.preferences.AppPreference;
import com.nanda.databindingexample.data.repo.AppRepo;
import com.nanda.databindingexample.data.response.common.AppResponse;

import javax.inject.Inject;

import io.realm.Realm;

public abstract class BaseViewModel<T> extends ViewModel {

    @Inject
    AppRepo appRepo;

    @Inject
    AppPreference appPreference;

    protected Realm appRealm = Realm.getInstance(RealmConfigurationFactory.createRealmConfiguration());

    protected final MutableLiveData<AppResponse<T>> response = new MutableLiveData<>();

    protected final MutableLiveData<Boolean> loadingStatus = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLoadingStatus() {
        return loadingStatus;
    }

    public MutableLiveData<AppResponse<T>> getResponse() {
        return response;
    }

    protected Realm refreshRealm() {
        return Realm.getInstance(RealmConfigurationFactory.createRealmConfiguration());
    }

}
