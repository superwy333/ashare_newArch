package com.zynsun.openplatform.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取每月天数帮助类
 */
public class CalendarUtil {

    /**
     * 获取当月第一天
     *
     * @return yyyy-MM-dd
     */
    public static Date getCurrentFristDay() {
        // 获取当前年份、月份、日期
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        String firstday = format.format(cale.getTime());
        return DateUtil.stringToDate(firstday, DateStyleEnum.YYYY_MM_DD);
    }

    /**
     * 获取当月最后一天
     *
     * @return yyyy-MM-dd
     */
    public static Date getCurrentLastDay() {
        // 获取当前年份、月份、日期
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        String lastday = format.format(cale.getTime());
        return DateUtil.stringToDate(lastday, DateStyleEnum.YYYY_MM_DD);
    }

    /**
     * 获取上个月第一天
     *
     * @return yyyy-MM-dd
     */
    public static Date getBeforeFristDay() {
        // 获取当前年份、月份、日期
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, -1);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        String firstday = format.format(cale.getTime());
        return DateUtil.stringToDate(firstday, DateStyleEnum.YYYY_MM_DD);
    }

    /**
     * 获取上个月最后一天
     *
     * @return yyyy-MM-dd
     */
    public static Date getBeforeLastDay() {
        // 获取当前年份、月份、日期
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        String lastday = format.format(cale.getTime());
        return DateUtil.stringToDate(lastday, DateStyleEnum.YYYY_MM_DD);
    }

    /**
     * 判断该日期是否在当月内
     *
     * @param date
     * @return
     */
    public static boolean betweenCurrentTime(Date date) {
        if (BeanUtil.isEmpty(date)) {
            return false;
        }
        if (date.compareTo(getCurrentFristDay()) == 0 || date.compareTo(getCurrentLastDay()) == 0) {
            return true;
        }
        return date.after(getCurrentFristDay()) && date.before(getCurrentLastDay());
    }

    /**
     * 判断该日期是否在上月内
     *
     * @param date
     * @return
     */
    public static boolean betweenLastTime(Date date) {
        if (BeanUtil.isEmpty(date)) {
            return false;
        }
        if (date.compareTo(getBeforeFristDay()) == 0 || date.compareTo(getBeforeLastDay()) == 0) {
            return true;
        }
        return date.after(getBeforeFristDay()) && date.before(getBeforeLastDay());
    }
}