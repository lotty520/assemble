package com.github.common;

import android.util.Log;

/**
 * @author lotty
 */
public class Common {

  private final static String TAG = "TAG";

  public static void entry() {
    Log.i(TAG, "common:this is android module ,test for aar merge");
    Log.i(TAG, "icon value is " + R.drawable.icon);
  }
}
