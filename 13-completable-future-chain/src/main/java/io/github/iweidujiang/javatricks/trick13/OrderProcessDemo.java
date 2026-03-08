package io.github.iweidujiang.javatricks.trick13;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示：CompletableFuture 链式异步处理订单
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/6
 */
public class OrderProcessDemo {
    public static void main(String[] args) {
        // 创建自定义线程池（避免使用默认 ForkJoinPool）
        ExecutorService executor = Executors.newFixedThreadPool(10);

        try {
            OrderProcessService service = new OrderProcessService(executor);

            System.out.println("=== 启动订单流程 ===");
            long start = System.currentTimeMillis();

            // 异步启动流程（非阻塞）
            CompletableFuture<String> future = service.processOrder("USER-123", "PROD-456");

            // 主线程可以做其他事情...
            System.out.println("【主线程】订单已提交，继续处理其他任务...");

            // 最终获取结果（此处可替换为 thenAccept 异步回调）
            String result = future.join(); // 阻塞等待结果（仅演示用）
            long duration = System.currentTimeMillis() - start;

            System.out.println("\n【最终结果】" + result);
            System.out.println("总耗时: " + duration + " ms");

        } finally {
            executor.shutdown();
        }
    }
}
