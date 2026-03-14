package io.github.iweidujiang.javatricks.trick20.basic;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基础示例与绕过技巧
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/14
 */
public class EffectivelyFinalDemo {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("hello", "world", "java", "lambda");

        // 错误示例（编译错误，已注释）
        // int total = 0;
        // words.forEach(s -> total += s.length());

        // 方案1：数组（非线程安全）
        int[] totalArray = {0};
        words.forEach(s -> totalArray[0] += s.length());
        System.out.println("Total length (array): " + totalArray[0]);

        // 方案2：AtomicInteger（线程安全）
        AtomicInteger totalAtomic = new AtomicInteger(0);
        words.forEach(s -> totalAtomic.addAndGet(s.length()));
        System.out.println("Total length (atomic): " + totalAtomic.get());

        // 方案3：Stream API（推荐）
        int totalStream = words.stream().mapToInt(String::length).sum();
        System.out.println("Total length (stream): " + totalStream);

        // effectively final 演示
        String prefix = "word: ";
        // prefix = "changed"; // 取消注释将导致编译错误
        words.forEach(s -> System.out.println(prefix + s));
    }
}
