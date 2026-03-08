package io.github.iweidujiang.javatricks.trick13;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * 订单处理服务：链式编排“下单 → 支付 → 发货”
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/6
 */
public class OrderProcessService {
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final ShippingService shippingService;

    public OrderProcessService(Executor executor) {
        this.orderService = new OrderService(executor);
        this.paymentService = new PaymentService(executor);
        this.shippingService = new ShippingService(executor);
    }

    /**
     * 执行完整订单流程
     *
     * @param userId    用户ID
     * @param productId 商品ID
     * @return 异步返回最终结果（运单号 或 错误信息）
     */
    public CompletableFuture<String> processOrder(String userId, String productId) {
        return orderService.createOrder(userId, productId)
                .thenCompose(orderId ->
                        paymentService.processPayment(orderId)
                                .thenCompose(paymentSuccess -> {
                                    if (paymentSuccess) {
                                        return shippingService.shipOrder(orderId);
                                    } else {
                                        return CompletableFuture.failedFuture(
                                                new RuntimeException("支付未成功，取消发货")
                                        );
                                    }
                                })
                )
                .handle((trackingNo, throwable) -> {
                    if (throwable != null) {
                        System.err.println("【流程异常】" + throwable.getMessage());
                        return "订单处理失败: " + throwable.getMessage();
                    } else {
                        return "订单完成！运单号: " + trackingNo;
                    }
                });
    }
}
