package io.github.iweidujiang.javatricks.trick10;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 平台线程 和 虚拟线程 HTTP 压测
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/27
 */
public class HttpLoadTest {
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    private static final HttpRequest REQUEST = HttpRequest.newBuilder()
            .uri(URI.create("https://httpbin.org/delay/1")) // 使用 HTTPS 更稳定
            .timeout(Duration.ofSeconds(10))
            .GET()
            .build();

    private static void runTest(String name, ExecutorService executor, int taskCount) {
        System.out.println("\n【" + name + "】发起 " + taskCount + " 次请求到 https://httpbin.org/delay/1");

        AtomicInteger success = new AtomicInteger(0);
        AtomicInteger failure = new AtomicInteger(0);
        long start = System.nanoTime();

        for (int i = 0; i < taskCount; i++) {
            executor.submit(() -> {
                try {
                    HttpResponse<?> response = CLIENT.send(REQUEST, HttpResponse.BodyHandlers.discarding());
                    if (response.statusCode() == 200) {
                        success.incrementAndGet();
                    } else {
                        failure.incrementAndGet();
                    }
                } catch (Exception e) {
                    failure.incrementAndGet();
                }
            });
        }

        // 等待所有任务完成
        executor.shutdown();
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        }

        long end = System.nanoTime();
        double durationSec = (end - start) / 1_000_000_000.0;
        double rps = taskCount / durationSec;

        System.out.printf(
                "✅ %s 完成 | 耗时: %.2f 秒 | 成功: %d | 失败: %d | 吞吐: %.1f RPS%n",
                name, durationSec, success.get(), failure.get(), rps
        );
    }

    public static void testPlatformHttp() {
        int taskCount = 1000;
        try (ExecutorService executor = Executors.newFixedThreadPool(200)) {
            runTest("平台线程池 (200)", executor, taskCount);
        }
    }

    public static void testVirtualHttp() {
        int taskCount = 1000;
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            runTest("虚拟线程", executor, taskCount);
        }
    }

    public static void main(String[] args) throws Exception {
        testPlatformHttp();
        testVirtualHttp();
    }
}
