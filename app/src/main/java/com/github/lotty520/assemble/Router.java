/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ckkj.router;

import android.app.Application;
import android.util.Log;

public class Router extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "init");
    }
}
