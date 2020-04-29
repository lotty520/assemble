package com.github.lotty520.assemble;

import android.app.Application;
import android.util.Log;

public class Router extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Log.e("TAG", "init");
  }
}
