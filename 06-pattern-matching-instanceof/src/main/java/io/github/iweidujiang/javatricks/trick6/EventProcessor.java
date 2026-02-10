package io.github.iweidujiang.javatricks.trick6;

import io.github.iweidujiang.javatricks.trick6.event.LoginEvent;
import io.github.iweidujiang.javatricks.trick6.event.PaymentEvent;

/**
 * 事件处理器实战
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/10
 */
public class EventProcessor {

    public void handle(Object event) {
        if (event instanceof LoginEvent e) {
            System.out.println("用户登录: " + e.username());
        } else if (event instanceof PaymentEvent e) {
            System.out.println("支付金额: " + e.amount());
        } else {
            System.out.println("未知事件");
        }
    }
}
