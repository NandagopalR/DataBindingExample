package com.nanda.databindingexample.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nanda.databindingexample.R;

import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {

    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setHeaderTitle(String title) {
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(title);
    }

    protected void showBackButton(boolean status) {
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(status);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        }
    }

    protected void showLoading() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setMessage("Fetching Books list...");
            dialog.show();
        } else dialog.show();
    }

    protected void hideLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
