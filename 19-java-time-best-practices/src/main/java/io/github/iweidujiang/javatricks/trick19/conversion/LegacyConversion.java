package io.github.iweidujiang.javatricks.trick19.conversion;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * 与旧API互转
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/14
 */
public class LegacyConversion {
    public static void main(String[] args) {
        // Date -> Instant
        Date oldDate = new Date();
        Instant instant = oldDate.toInstant();
        System.out.println("Instant from Date: " + instant);

        // Instant -> Date
        Date newDate = Date.from(instant);
        System.out.println("Date from Instant: " + newDate);

        // Calendar -> Instant
        Calendar calendar = Calendar.getInstance();
        Instant instantFromCal = calendar.toInstant();
        System.out.println("Instant from Calendar: " + instantFromCal);

        // Date -> LocalDateTime (system default zone)
        LocalDateTime localDateTime = oldDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        System.out.println("LocalDateTime from Date: " + localDateTime);

        // LocalDateTime -> Date
        LocalDateTime now = LocalDateTime.now();
        Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("Date from LocalDateTime: " + date);
    }
}
