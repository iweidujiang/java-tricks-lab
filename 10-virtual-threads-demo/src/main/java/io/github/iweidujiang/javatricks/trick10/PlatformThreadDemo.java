package io.github.iweidujiang.javatricks.trick10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 平台线程
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/27
 */
public class PlatformThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("【平台线程】启动 1000 个任务（更多会 OOM）...");

        int taskCount = 1000; // 不要超过 5000，否则可能 OOM
        long start = System.currentTimeMillis();

        try (ExecutorService executor = Executors.newFixedThreadPool(200)) {
            for (int i = 0; i < taskCount; i++) {
                final int id = i;
                executor.submit(() -> {
                    try {
                        Thread.sleep(50); // 模拟 I/O
                        if (id % 200 == 0) {
                            System.out.println("平台线程任务 " + id + " 完成");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }

        long duration = System.currentTimeMillis() - start;
        System.out.println("平台线程完成 " + taskCount + " 任务，耗时: " + duration + " ms\n");
    }
}
