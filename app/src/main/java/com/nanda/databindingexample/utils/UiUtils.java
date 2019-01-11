package com.nanda.databindingexample.utils;

import android.content.Context;
import android.widget.Toast;

public class UiUtils {

    public static void showToast(Context context, String input) {
        Toast.makeText(context, input, Toast.LENGTH_SHORT).show();
    }

}
