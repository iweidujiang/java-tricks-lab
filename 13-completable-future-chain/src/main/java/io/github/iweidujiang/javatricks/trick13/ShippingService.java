package io.github.iweidujiang.javatricks.trick13;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * 模拟发货服务
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/6
 */
public class ShippingService {
    private final Executor executor;

    public ShippingService(Executor executor) {
        this.executor = executor;
    }

    /**
     * 发货
     * @param orderId 订单ID
     * @return 异步返回发货状态
     */
    public CompletableFuture<String> shipOrder(String orderId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("【发货服务】开始发货，订单: " + orderId);
            simulateDelay(600);
            String trackingNo = "SF" + System.currentTimeMillis();
            System.out.println("【发货服务】发货成功，运单号: " + trackingNo);
            return trackingNo;
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
