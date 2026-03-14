package io.github.iweidujiang.javatricks.trick19.basic;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

/**
 * 基本操作示例
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/14
 */
public class BasicExamples {
    public static void main(String[] args) {
        // 创建
        LocalDate today = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedNow = ZonedDateTime.now();
        Instant instant = Instant.now();

        System.out.println("today: " + today);
        System.out.println("nowTime: " + nowTime);
        System.out.println("now: " + now);
        System.out.println("zonedNow: " + zonedNow);
        System.out.println("instant: " + instant);

        // 指定日期
        LocalDate date = LocalDate.of(2025, 3, 14);
        LocalTime time = LocalTime.of(14, 30);
        LocalDateTime dateTime = LocalDateTime.of(2025, 3, 14, 14, 30);

        // 加减
        LocalDate tomorrow = today.plusDays(1);
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate lastWeek = today.minusWeeks(1);
        System.out.println("tomorrow: " + tomorrow);

        // 调整
        LocalDate firstDay = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("firstDay: " + firstDay);
        System.out.println("nextMonday: " + nextMonday);
    }
}
