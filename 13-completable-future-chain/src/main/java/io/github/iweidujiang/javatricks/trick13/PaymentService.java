package io.github.iweidujiang.javatricks.trick13;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * 模拟支付服务
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/6
 */
public class PaymentService {
    private final Executor executor;

    public PaymentService(Executor executor) {
        this.executor = executor;
    }

    /**
     * 执行支付
     * @param orderId 订单ID
     * @return 异步返回支付结果（true=成功）
     */
    public CompletableFuture<Boolean> processPayment(String orderId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("【支付服务】正在处理支付，订单: " + orderId);
            simulateDelay(800);
            // 模拟 90% 支付成功率
            boolean success = Math.random() > 0.1;
            if (success) {
                System.out.println("【支付服务】支付成功: " + orderId);
            } else {
                System.out.println("【支付服务】支付失败: " + orderId);
                throw new RuntimeException("支付网关超时");
            }
            return success;
        }, executor);
    }

    private void simulateDelay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
