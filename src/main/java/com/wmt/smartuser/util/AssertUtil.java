package com.wmt.smartuser.util;

import lombok.experimental.UtilityClass;

/**
 * @author wmtumanday
 */
@UtilityClass
public class AssertUtil {

    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
    public static void isFalse(boolean condition, String message) {
        if (condition) {
            throw new IllegalArgumentException(message);
        }
    }
     public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }
//    public static void notBlank(String str, String message) {
//        if (StringUtil.isEmpty(str)) {
//            throw new IllegalArgumentException(message);
//        }
//    }
//    public static void isBlank(String str, String message) {
//        if (StringUtil.isNotEmpty(str)) {
//            throw new IllegalArgumentException(message);
//        }
//    }


}
