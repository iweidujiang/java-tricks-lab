package io.github.iweidujiang.javatricks.trick05.orders;

/**
 * 安全处理状态
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/10 15:37
 */
public class OrderStatusHandler {
    public static String getMessage(OrderStatus status) {
        return switch (status) {
            case Pending p -> "订单待确认";
            case Confirmed c -> "已确认，时间: " + c.confirmTime();
            case Shipped s -> "已发货，单号: " + s.trackingNumber();
            case Cancelled c -> "已取消，原因: " + c.reason();
        };
    }
}
