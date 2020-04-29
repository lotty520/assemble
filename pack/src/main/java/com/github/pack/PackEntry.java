package com.github.pack;

import android.util.Log;
import com.github.common.Common;
import com.github.jar.Jar;

public class PackEntry {
  private final static String TAG = "TAG";

  static {
    Log.i(TAG, "pack:this is pack entry point, it will call other submodule api");
  }

  public static void init() {
    Jar.entry();
    Common.entry();
  }
}
