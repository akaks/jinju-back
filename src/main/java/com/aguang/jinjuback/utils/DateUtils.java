package com.aguang.jinjuback.utils;

public class DateUtils {

    /**
     * 获取当前时间（精确到秒）
     * @return long类型
     */
    public static Long getCurrentTimeForInt() {
        Long l = System.currentTimeMillis();
        l = l / 1000;
        return l;
    }
}
