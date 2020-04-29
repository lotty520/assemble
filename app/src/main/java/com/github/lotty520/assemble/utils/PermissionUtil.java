package com.ckkj.router.utils;

import android.content.Context;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * @ProjectName: hybridApp
 * @Package: cn.tjcy.hybrid.util
 * @ClassName: PermissionUtil
 * @Description: java类作用描述
 * @Author: 吴晗
 */
public class PermissionUtil {
    public static boolean hasPermission(Context ctx, String[] permission) {
        if (permission.length > 0) {
            return EasyPermissions.hasPermissions(ctx, permission);
        } else {
            return true;
        }
    }
}
