package com.ckkj.router.utils;

public class StringUtil {

    private static final String HEXES = "0123456789ABCDEF";

    /**
     * @param raw 原始字节
     */
    public static String hexToString(byte[] raw) {
        if (raw != null) {
            StringBuilder hex = new StringBuilder(2 * raw.length);
            for (byte b : raw) {
                hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
            }
            return hex.toString();
        }
        return null;
    }
}
