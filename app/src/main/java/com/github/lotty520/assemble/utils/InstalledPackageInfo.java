/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ckkj.router.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lotty
 */
public class InstalledPackageInfo {

    /**
     * 应用名称
     */
    private String name;
    /**
     * 应用包名
     */
    private String package_name;
    /**
     * 应用版本号
     */
    private String version;
    /**
     * 安装时间:毫秒时间戳
     */
    private long deploy_time;

    /**
     * 更新时间:毫秒时间戳
     */
    private long update_time;


    public static List<InstalledPackageInfo> collectInfo(Context context) {
        List<InstalledPackageInfo> installedList = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            InstalledPackageInfo info = new InstalledPackageInfo();
            info.name = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
            info.package_name = packageInfo.packageName;
            // versionName 可能为空
            info.version = packageInfo.versionName == null ? "" : packageInfo.versionName;
            info.deploy_time = packageInfo.firstInstallTime;
            info.update_time = packageInfo.lastUpdateTime;
            installedList.add(info);
        }
        return installedList;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("package_name", package_name);
            jsonObject.put("version", version);
            jsonObject.put("deploy_time", deploy_time);
            jsonObject.put("update_time", update_time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
