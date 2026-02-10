package io.github.iweidujiang.javatricks.trick05.orders;

/**
 * 订单状态接口
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/10 15:34
 */
public sealed interface OrderStatus permits Pending, Confirmed, Shipped, Cancelled {}
