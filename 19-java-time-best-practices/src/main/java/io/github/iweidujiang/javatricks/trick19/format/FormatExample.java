package io.github.iweidujiang.javatricks.trick19.format;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * 格式化与解析
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/14
 */
public class FormatExample {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        // 格式化
        String dateStr = date.format(DATE_FORMATTER);
        String dateTimeStr = dateTime.format(DATETIME_FORMATTER);
        System.out.println("Formatted date: " + dateStr);
        System.out.println("Formatted datetime: " + dateTimeStr);

        // 解析
        LocalDate parsedDate = LocalDate.parse("2025-03-14", DATE_FORMATTER);
        LocalDateTime parsedDateTime = LocalDateTime.parse("2025-03-14 14:30:00", DATETIME_FORMATTER);
        System.out.println("Parsed date: " + parsedDate);
        System.out.println("Parsed datetime: " + parsedDateTime);

        // 使用内置格式化器
        String isoDate = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String isoDateTime = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("ISO date: " + isoDate);
        System.out.println("ISO datetime: " + isoDateTime);

        // 本地化格式
        String localized = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println("Localized full: " + localized);
    }
}
