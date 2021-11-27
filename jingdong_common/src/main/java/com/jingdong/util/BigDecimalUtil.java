package com.jingdong.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BigDecimalUtil {

    /**
     * 如果值为null则默认为0 (BigDecimal.ZERO)
     * @param bigDecimal
     * */
    public static BigDecimal defaultZeroIfNull(BigDecimal bigDecimal){
        if(null == bigDecimal){
            return BigDecimal.ZERO;
        }
        return bigDecimal;
    }

    /**
     * 如果值为null则返回默认值
     * @param bigDecimal
     * */
    public static BigDecimal defaultIfNull(BigDecimal bigDecimal,BigDecimal defaultVal){
        if(null == bigDecimal){
            return defaultVal;
        }
        return bigDecimal;
    }

    public static boolean isNullOrZero(BigDecimal bigDecimal){
        if(null == bigDecimal || (BigDecimal.ZERO.compareTo(bigDecimal) == 0)){
            return true;
        }
        return false;
    }

    public static String formatTwoPrecision(BigDecimal bigDecimal){
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(bigDecimal);
    }

    public static BigDecimal rounding(BigDecimal bigDecimal,int scale){
        return bigDecimal.setScale(scale, RoundingMode.HALF_UP);
    }

}
