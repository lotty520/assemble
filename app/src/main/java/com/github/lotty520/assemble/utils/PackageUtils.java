package com.ckkj.router.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PackageUtils {

    public static Map<String, String> installedPackages(Context context) {
        PackageManager packageManager = context.getPackageManager();
        StringBuilder complete = new StringBuilder();
        StringBuilder demotion = new StringBuilder();

        String packageName = null;
        String appName = null;
        List packages = null;
        packages = packageManager.getInstalledPackages(0);

        if (packages == null || packages.size() == 0) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            packages = packageManager.queryIntentActivities(intent, 0);
        }
        Iterator item = packages.iterator();
        final String[] sysPrefixfilter = new String[]{  //系统应用过滤包名前缀
                "com.android.",
                "com.google.",
                "com.qualcomm.",
                "google.android.",
                "com.huawei.",   //华为  HONOR  HUAWEI
                "com.miui.",     //Xiaomi
                "com.xiaomi.",   //Xiaomi
                "com.vivo.",     //vivo
                "com.oppo.",     //OPPO
                "com.samsung.",  //samsung
                "com.sec.android.",
                "com.lenovo.",  //Lenovo
                "com.yulong.",  //Coolpad  ivvi
                "com.gionee.", //GiONEE
                "com.letv.",     //LeEco  Letv
                "com.aliyun.",   //feixun 4G
                "com.yunos.",    //feixun 4G
                "com.sony",     //Sony
                "com.htc.",    //htc
                "com.zte.",  //ZTE
                "com.meitu.",  //Meitu
                "com.marvell.",  //xiaolajiao marvell YUSUN
                "com.lge.",    //lge
                "com.doov",   //DOOV
                "com.oneplus.",  //OnePlus
                "com.meizu.",     //Meizu
                "com.hisense.", //Hisense
                "com.qiku.",   //360
                "com.wingtech.",   //CMDC
                "com.motorola.",  //motorola
                "cn.nubia.",   //nubia
                "com.tcl",     //tcl
                "com.prize.",   //koobee
                "com.smartisan",  //SMARTISAN
                "com.zui.",      //ZUK
                "com.greenorange.",   //GO
                "com.evenwell.",   // Nokia
                "com.qiku.",     //lephone
                "com.freeme.",   //4G
                "com.asus.",  //asus
                "com.readboy.",  //qcom
                "com.changhong.",   //Changhong
                "com.cootek.",// 触宝
                "com.amigo.", // amigo
                "com.mediatek."//联发科

        };
        final String[] sysSuffixfilter = new String[]{  //系统应用过滤包名后缀
                ".cmcc"
        };
        final String[] Prefixfilter = new String[]{   //非系统应用过滤包名前缀
                "CheilPengtai."
        };
        boolean filterFlag;
        int indexFlag = 0;
        int i = 0;
        Map<String, String> InstallPackageMap = new HashMap<String, String>();
        while (item.hasNext()) {
            Object Info = item.next();
            PackageInfo packageInfo = null;
            ApplicationInfo appInfo = null;

            if (Info instanceof ResolveInfo) {
                appInfo = ((ResolveInfo) Info).activityInfo.applicationInfo;
                packageName = ((ResolveInfo) Info).activityInfo.packageName;
            }
            if (Info instanceof PackageInfo) {
                packageInfo = (PackageInfo) Info;
                appInfo = packageInfo.applicationInfo;
                packageName = appInfo.packageName;
            }
            //PackageInfo packageInfo = (PackageInfo) item.next();
            //ApplicationInfo appInfo = packageInfo.applicationInfo;
            //packageName = appInfo.packageName;

            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {   //系统应用过滤包名
                filterFlag = false;
                for (String s : sysPrefixfilter) {
                    if (packageName.startsWith(s)) {
                        filterFlag = true;
                        String num = InstallPackageMap.get(s);
                        if (num == null) {
                            InstallPackageMap.put(s, "1");
                        } else
                            InstallPackageMap.put(s, Integer.toString(Integer.parseInt(num) + 1));

                        break;
                    }
                }
                for (String s : sysSuffixfilter) {
                    if (packageName.endsWith(s)) {
                        filterFlag = true;
                        break;
                    }
                }
                if (filterFlag || "android".equals(packageName)) {
                    continue;
                }


            }
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {   //非系统应用采集前100个包名和前20个中文名
                // 此处比较耗费时间
                filterFlag = false;
                for (String s : Prefixfilter) {
                    if (packageName.startsWith(s)) {

                        filterFlag = true;
                        break;
                    }
                }
                if (filterFlag) {
                    continue;
                }
                if (i < 20) {
                    appName = packageManager.getApplicationLabel(appInfo).toString();

                } else appName = "-";
                i++;
                if (i > 100) break;
                if (complete.length() > 0) {
                    complete.append(",");
                }
                complete.append(packageName + ":" + appName);

                if (demotion.length() > 0) {
                    demotion.append(",");
                }
                demotion.append(packageName).append(":").append(appName);
            }
        }
        for (String eachPackage : InstallPackageMap.keySet()) {
            if (Integer.parseInt(InstallPackageMap.get(eachPackage)) > 5) {
                if (complete.length() > 0) {
                    complete.append(",");
                }
                complete.append(eachPackage + "*" + InstallPackageMap.get(eachPackage));
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("complete", complete.toString());
        map.put("demotion", demotion.toString());
        return map;
    }

}
