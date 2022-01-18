package com.jingdong.util;

import java.util.regex.Pattern;

public class CommonCheckUtil {

    /**
     * 是否是整数类型
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        String trim = str.trim();
        if (trim == null || trim.length() > 8) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(trim).matches();
    }
}
