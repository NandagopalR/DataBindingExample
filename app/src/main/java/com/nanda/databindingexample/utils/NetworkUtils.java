package com.nanda.databindingexample.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetworkUtils {

    public static void turnOffGps(Context context) {
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", false);
        context.sendBroadcast(intent);
    }

    public static void turnOnGps(Context context) {
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", true);
        context.sendBroadcast(intent);
    }

    /**
     * Check the Internet connection available status
     *
     * @param context - Context environment passed by this parameter
     * @return boolean true if the Internet Connection is Available and false otherwise
     */
    public static boolean isConnected(Context context) {
        //Connectivity manager instance
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Fetch Active internet connection from network info
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        // return the network connection is active or not.
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
