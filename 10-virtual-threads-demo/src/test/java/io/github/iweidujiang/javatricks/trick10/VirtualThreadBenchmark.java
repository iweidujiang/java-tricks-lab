package io.github.iweidujiang.javatricks.trick10;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * JMH 微基准测试：虚拟线程 vs 平台线程
 * <p>
 * 测试场景：并发执行 N 个任务，每个任务 sleep(100ms) 模拟 I/O 阻塞
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/27
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class VirtualThreadBenchmark {
    // 总任务数
    private static final int TASK_COUNT = 10_000;

    // 共享的平台线程池（固定 200 线程）
    private ExecutorService platformExecutor;

    // 每次 benchmark 创建新的虚拟线程执行器（符合其设计语义）
    private ExecutorService virtualExecutor;

    @Setup
    public void setup() {
        platformExecutor = Executors.newFixedThreadPool(200);
    }

    @TearDown
    public void tearDown() {
        platformExecutor.shutdown();
        if (virtualExecutor != null) {
            virtualExecutor.shutdown();
        }
    }

    /**
     * 模拟单个 I/O 任务：sleep 100ms
     */
    private void simulateIoTask() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 【Baseline】直接顺序执行（仅用于参考，不代表并发）
     */
    @Benchmark
    public void testSequential(Blackhole bh) {
        for (int i = 0; i < 100; i++) { // 只跑 100 次，否则太慢
            simulateIoTask();
            bh.consume(i);
        }
    }

    /**
     * 【平台线程】使用固定线程池并发执行 TASK_COUNT 个任务
     */
    @Benchmark
    public void testPlatformThreads() throws InterruptedException {
        var futures = new java.util.ArrayList<java.util.concurrent.Future<?>>();
        for (int i = 0; i < TASK_COUNT; i++) {
            futures.add(platformExecutor.submit(this::simulateIoTask));
        }
        // 等待所有任务完成
        for (var future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                // ignore
            }
        }
    }

    /**
     * 【虚拟线程】使用虚拟线程执行器并发执行 TASK_COUNT 个任务
     */
    @Benchmark
    public void testVirtualThreads() throws InterruptedException {
        virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
        var futures = new java.util.ArrayList<java.util.concurrent.Future<?>>();
        for (int i = 0; i < TASK_COUNT; i++) {
            futures.add(virtualExecutor.submit(this::simulateIoTask));
        }
        // 等待所有任务完成
        for (var future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                // ignore
            }
        }
        virtualExecutor.close(); // 关闭以 join 所有虚拟线程
        virtualExecutor = null;
    }
}
