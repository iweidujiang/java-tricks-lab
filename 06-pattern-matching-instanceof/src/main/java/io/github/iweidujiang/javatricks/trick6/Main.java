package io.github.iweidujiang.javatricks.trick6;

import io.github.iweidujiang.javatricks.trick6.event.LoginEvent;
import io.github.iweidujiang.javatricks.trick6.event.PaymentEvent;

/**
 * 主演示程序入口
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/10
 */
public class Main {
    public static void main(String[] args) {
        BasicDemo.run();
        MultiTypeDispatcher.run();
        NullSafetyDemo.run();

        System.out.println("\n=== 事件处理器实战 ===");
        var processor = new EventProcessor();
        processor.handle(new LoginEvent("苏渡苇"));
        processor.handle(new PaymentEvent(99.9));
    }
}
