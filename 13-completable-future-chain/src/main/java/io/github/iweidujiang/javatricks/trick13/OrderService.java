package io.github.iweidujiang.javatricks.trick13;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * 模拟下单服务（耗时操作）
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/6
 */
public class OrderService {

    private final Executor executor;

    public OrderService(Executor executor) {
        this.executor = executor;
    }

    /**
     * 创建订单
     * @param userId 用户ID
     * @param productId 商品ID
     * @return 异步返回订单ID
     */
    public CompletableFuture<String> createOrder(String userId, String productId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("【下单服务】正在创建订单，用户: " + userId + ", 商品: " + productId);
            simulateDelay(500); // 模拟网络延迟
            String orderId = "ORDER-" + System.currentTimeMillis();
            System.out.println("【下单服务】订单创建成功: " + orderId);
            return orderId;
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
