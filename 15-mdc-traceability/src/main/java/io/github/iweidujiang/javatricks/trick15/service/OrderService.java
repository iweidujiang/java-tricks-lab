package io.github.iweidujiang.javatricks.trick15.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 模拟订单服务，包含同步和异步操作。
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/10
 */
@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    /**
     * 同步创建订单（在主线程执行，MDC 正常）
     */
    public void createOrder(String orderId) {
        log.info("开始创建订单: {}", orderId);
        // 模拟业务逻辑
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("同步--->订单 {} 创建成功", orderId);
    }

    /**
     * 异步处理支付（在线程池执行，依赖 TTL 传递 MDC）
     */
    public void processPaymentAsync(String orderId) {
        log.info("开始异步处理支付: {}", orderId);
        // 模拟耗时操作
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("异步--->订单 {} 支付处理完成", orderId);
    }
}
