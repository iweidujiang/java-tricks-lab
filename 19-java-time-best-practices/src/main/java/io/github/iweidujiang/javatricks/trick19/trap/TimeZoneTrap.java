package io.github.iweidujiang.javatricks.trick19.trap;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 时区陷阱演示
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/14
 */
public class TimeZoneTrap {
    public static void main(String[] args) {
        // 错误：用 LocalDateTime 存储跨时区时间
        LocalDateTime orderTime = LocalDateTime.now(); // 假设用户在纽约
        // 存入数据库后，在其他时区取出，无法知道原时区，导致时间错误

        // 正确：使用 ZonedDateTime 或 Instant
        ZonedDateTime correctOrderTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        Instant instant = correctOrderTime.toInstant();

        System.out.println("Correct order time (New York): " + correctOrderTime);
        System.out.println("Instant (UTC): " + instant);
        System.out.println("Converted to Shanghai: " + instant.atZone(ZoneId.of("Asia/Shanghai")));
    }
}
