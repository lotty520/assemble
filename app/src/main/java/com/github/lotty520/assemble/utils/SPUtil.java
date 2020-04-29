package com.ckkj.router.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author lotty
 * @date 2018/3/29
 */

public class SPUtil {

    private static final String SP_NAME = "hybrid_sp";
    private static SharedPreferences sharedPreferences;

    public static void init(Context ctx) {
        sharedPreferences = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static String get(String key) {
        return sharedPreferences.getString(key, "");
    }

    public static void put(String key, String value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.apply();
    }


    public static int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

}
