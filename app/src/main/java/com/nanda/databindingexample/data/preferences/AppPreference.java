package com.nanda.databindingexample.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class AppPreference {

    private static final String PREFERENCE_NAME = "DATABINDING_PREF";
    private static final String IS_LOGGEDIN = "IS_LOGGEDIN";

    private SharedPreferences preferences;

    @Inject
    public AppPreference(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void clearAppPreference() {
        preferences.edit().clear().apply();
    }

    public void setIsLoggedin(boolean isLoggedin) {
        preferences.edit().putBoolean(IS_LOGGEDIN, isLoggedin).apply();
        ;
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(IS_LOGGEDIN, false);
    }

}
