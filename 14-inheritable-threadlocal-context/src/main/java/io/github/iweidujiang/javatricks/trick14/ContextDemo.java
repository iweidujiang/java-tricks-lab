package io.github.iweidujiang.javatricks.trick14;

import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 主程序（三方案并行对比）
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/8
 */
public class ContextDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=".repeat(60));
        System.out.println("【方案1】普通 ThreadLocal —— 线程池中必然失效");
        testSimple();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("【方案2】InheritableThreadLocal —— 线程池中因线程复用而失效");
        testInheritable();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("【方案3】手动包装 Runnable —— 线程池中可靠传递");
        testManualWrapper();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("【方案4】Alibaba TTL + 装饰线程池 —— 生产级推荐方案");
        testTtl();
    }

    private static void testSimple() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1); // 单线程池，确保复用
        SimpleContext context = new SimpleContext();

        context.setUserId("USER-A");
        executor.submit(() -> {
            String id = context.getUserId();
            System.out.println("  → 任务A（期望 USER-A）: 实际=" + (id != null ? id : "null"));
        });

        context.setUserId("USER-B");
        executor.submit(() -> {
            String id = context.getUserId();
            System.out.println("  → 任务B（期望 USER-B）: 实际=" + (id != null ? id : "null"));
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
    }

    private static void testInheritable() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        InheritableContext context = new InheritableContext();

        context.setUserId("USER-A");
        executor.submit(() -> {
            String id = context.getUserId();
            System.out.println("  → 任务A（期望 USER-A）: 实际=" + (id != null ? id : "null"));
        });

        context.setUserId("USER-B");
        executor.submit(() -> {
            String id = context.getUserId();
            System.out.println("  → 任务B（期望 USER-B）: 实际=" + (id != null ? id : "null"));
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
    }

    private static void testManualWrapper() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        SimpleContext context = new SimpleContext();

        // 关键：在设置 USER-A 后立即 wrap
        context.setUserId("USER-A");
        Runnable taskA = ContextWrapper.wrap(context, () -> {
            String id = context.getUserId();
            System.out.println("  → 任务A（期望 USER-A）: 实际=" + (id != null ? id : "null"));
        });

        // 再设置 USER-B 后 wrap
        context.setUserId("USER-B");
        Runnable taskB = ContextWrapper.wrap(context, () -> {
            String id = context.getUserId();
            System.out.println("  → 任务B（期望 USER-B）: 实际=" + (id != null ? id : "null"));
        });

        executor.submit(taskA);
        executor.submit(taskB);

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
    }

    private static void testTtl() throws InterruptedException {
        ExecutorService rawPool = Executors.newFixedThreadPool(1);
        ExecutorService ttlPool = TtlExecutors.getTtlExecutorService(rawPool);
        TtlContext context = new TtlContext();

        context.setUserId("USER-A");
        ttlPool.submit(() -> {
            String id = context.getUserId();
            System.out.println("  → 任务A（期望 USER-A）: 实际=" + (id != null ? id : "null"));
        });

        context.setUserId("USER-B");
        ttlPool.submit(() -> {
            String id = context.getUserId();
            System.out.println("  → 任务B（期望 USER-B）: 实际=" + (id != null ? id : "null"));
        });

        ttlPool.shutdown();
        ttlPool.awaitTermination(1, TimeUnit.SECONDS);
    }
}
