package io.github.iweidujiang.javatricks.trick05.orders;

/**
 * 订单已发货
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/10 15:36
 */
public record Shipped(String trackingNumber) implements OrderStatus {}
