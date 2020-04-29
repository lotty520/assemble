package com.github.lotty520.assemble;

import android.app.Application;
import android.util.Log;

/**
 * @author lotty
 */
public class MergeApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Log.e("TAG", "init");

  }
}
