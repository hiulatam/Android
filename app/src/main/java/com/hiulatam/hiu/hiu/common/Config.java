package com.hiulatam.hiu.hiu.common;

import android.nfc.Tag;
import android.util.Log;

/**
 * Created by:  Shiny Solutions
 * Created on:  10/4/17.
 */

public class Config {

    public static final String TAG = "com.hiulatam.hiu.hiu";

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String EXTRA_CELEBRITY_ITEM = "EXTRA_CELEBRITY_ITEM";

    public static final void logInfo(String message){
        Log.i(TAG, message);
    }

}
