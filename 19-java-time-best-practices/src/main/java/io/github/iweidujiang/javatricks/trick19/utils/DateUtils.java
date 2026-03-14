package io.github.iweidujiang.javatricks.trick19.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * 封装的工具类
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/14
 */
public class DateUtils {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 获取一天的开始时间
    public static LocalDateTime startOfDay(LocalDate date) {
        return date.atStartOfDay();
    }

    // 获取一天的结束时间
    public static LocalDateTime endOfDay(LocalDate date) {
        return date.atTime(LocalTime.MAX);
    }

    // 计算两个日期之间的天数差
    public static long daysBetween(LocalDate start, LocalDate end) {
        return Period.between(start, end).getDays();
    }

    // 精确天数差（基于24小时）
    public static long exactDaysBetween(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).toDays();
    }

    // 字符串转LocalDate
    public static LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    // LocalDate转字符串
    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    // 获取当前月份的第一天
    public static LocalDate firstDayOfMonth() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    }

    // 获取当前月份的最后一天
    public static LocalDate lastDayOfMonth() {
        return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
    }
}
