package com.ckkj.router.utils;

import android.content.Context;

/**
 * @ProjectName: router
 * @Package: com.ckkj.router.utils
 * @ClassName: ResourceUtil
 * @Description: 资源类工具类
 * @Author: 吴晗
 */
public class ResourceUtil {

    /**
     * dp转像素
     */
    public static int dp2px(Context ctx, int dp) {
        return (int) (dp * density(ctx) + 0.5);
    }

    /**
     * 屏幕密度,返回浮点型
     */
    public static float density(Context ctx) {
        return ctx.getResources().getDisplayMetrics().density;
    }

    public static int screenWitdh(Context ctx) {
        return ctx.getResources().getDisplayMetrics().widthPixels;
    }

    public static int screenHeight(Context ctx) {
        return ctx.getResources().getDisplayMetrics().heightPixels;
    }
}
