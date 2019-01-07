package com.nanda.databindingexample.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import dagger.android.support.DaggerFragment;

public class BaseFragment extends DaggerFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
