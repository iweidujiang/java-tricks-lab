package io.github.iweidujiang.javatricks.trick20.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程安全性对比
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/14
 */
public class ThreadSafetyDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        // 线程不安全的数组方式
        int[] unsafeCount = {0};
        list.parallelStream().forEach(i -> unsafeCount[0]++);
        System.out.println("Unsafe count: " + unsafeCount[0]); // 大概率小于1000

        // 线程安全的 AtomicInteger
        AtomicInteger safeCount = new AtomicInteger(0);
        list.parallelStream().forEach(i -> safeCount.incrementAndGet());
        System.out.println("Safe count: " + safeCount.get()); // 总是1000
    }
}
