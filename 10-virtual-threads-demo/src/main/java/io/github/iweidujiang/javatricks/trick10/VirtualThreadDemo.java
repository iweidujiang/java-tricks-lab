package io.github.iweidujiang.javatricks.trick10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 虚拟线程
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/27
 */
public class VirtualThreadDemo {
    public static void main(String[] args) {
        System.out.println("【虚拟线程】启动 100000 个任务...");

        int taskCount = 100_000;
        long start = System.currentTimeMillis();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < taskCount; i++) {
                final int id = i;
                executor.submit(() -> {
                    try {
                        Thread.sleep(50); // 模拟 I/O
                        if (id % 20_000 == 0) {
                            System.out.println("虚拟线程任务 " + id + " 完成");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        } // 自动 join 所有虚拟线程

        long duration = System.currentTimeMillis() - start;
        System.out.println("虚拟线程完成 " + taskCount + " 任务，耗时: " + duration + " ms\n");
    }
}
