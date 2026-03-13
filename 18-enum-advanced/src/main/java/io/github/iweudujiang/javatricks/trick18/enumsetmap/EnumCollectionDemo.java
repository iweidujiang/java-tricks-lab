package io.github.iweudujiang.javatricks.trick18.enumsetmap;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

/**
 * EnumSet/EnumMap 示例
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/12
 */
public class EnumCollectionDemo {
    public static void main(String[] args) {
        // EnumSet
        EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        EnumSet<Day> workdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        System.out.println("周末: " + weekend);
        System.out.println("工作日: " + workdays);

        // EnumMap
        EnumMap<Day, String> schedule = new EnumMap<>(Day.class);
        schedule.put(Day.MONDAY, "开会");
        schedule.put(Day.TUESDAY, "写代码");
        schedule.put(Day.FRIDAY, "总结");

        for (Map.Entry<Day, String> entry : schedule.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
