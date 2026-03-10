package io.github.iweidujiang.javatricks.trick15.controller;

import com.alibaba.ttl.TtlRunnable;
import io.github.iweidujiang.javatricks.trick15.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * 订单控制器，演示 MDC 在同步 + 异步场景下的传递效果。
 * <p>
 * 请求流程：
 * 1. 拦截器自动注入 traceId 到 MDC
 * 2. 主线程日志带 traceId
 * 3. 异步任务提交到 TTL 装饰的线程池
 * 4. 子线程日志也带相同 traceId
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/10
 */
@RestController
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final Executor traceableTaskExecutor;

    public OrderController(OrderService orderService,
                           @Qualifier("traceableTaskExecutor") Executor traceableTaskExecutor) {
        this.orderService = orderService;
        this.traceableTaskExecutor = traceableTaskExecutor;
    }

    @GetMapping("/ttl-test")
    public String ttlTest() {
        MDC.put("traceId", "TTL9999");
        log.info("主线程日志");

        // 直接使用 traceableTaskExecutor 提交 Runnable
        traceableTaskExecutor.execute(() -> {
            log.info("TTL 线程池中的日志");
        });

        MDC.clear();
        return "Check logs";
    }

    @GetMapping("/order")
    public String createOrder() {
        System.out.println(">>> 使用的 Executor 类型: " + traceableTaskExecutor.getClass().getName());

        String orderId = "ORDER-" + System.currentTimeMillis() % 10000;

        log.info("收到创建订单请求: {}", orderId);

        // 同步操作（主线程）
        orderService.createOrder(orderId);

        /*// 异步操作（提交到 TTL 装饰的线程池）
        CompletableFuture.runAsync(() -> {
            orderService.processPaymentAsync(orderId);
        }, traceableTaskExecutor);*/

        /*// 显式包装 Runnable
        Runnable paymentTask = () -> orderService.processPaymentAsync(orderId);
        Runnable ttlPaymentTask = TtlRunnable.get(paymentTask);
        traceableTaskExecutor.execute(ttlPaymentTask);*/

        Map<String, String> mdc = MDC.getCopyOfContextMap();
        traceableTaskExecutor.execute(() -> {
            if (mdc != null) MDC.setContextMap(mdc);
            try {
                orderService.processPaymentAsync(orderId);
            } finally {
                MDC.clear();
            }
        });

        log.info("订单请求已受理，orderId={}", orderId);
        return "OK";
    }
}
