package io.github.iweidujiang.javatricks.trick05.orders;

/**
 * 订单取消
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/10 15:36
 */
public record Cancelled(String reason) implements OrderStatus {}
