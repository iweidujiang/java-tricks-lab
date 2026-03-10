package io.github.iweidujiang.javatricks.trick15.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步任务线程池配置。
 * <p>
 * 关键点：
 * - 使用 ThreadPoolTaskExecutor 创建 Spring 管理的线程池
 * - 通过 TtlExecutors.getTtlExecutorService() 装饰线程池
 * - 装饰后，提交的任务能自动继承：
 *     1. MDC 上下文（traceId）
 *     2. 自定义 ThreadLocal（如用户信息）
 * - 无需手动包装 Runnable
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/10
 */
@Configuration
@EnableAsync
public class ExecutorConfig {
    /**
     * 定义一个名为 "traceableTaskExecutor" 的线程池 Bean。
     * 所有需要传递 traceId 的异步任务都应使用此执行器。
     */
    @Bean("traceableTaskExecutor")
    public Executor traceableTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("traceable-task-");
        executor.initialize();

        // 【核心】使用 TTL 装饰线程池，使其支持 MDC 和 ThreadLocal 传递
        return TtlExecutors.getTtlExecutorService(executor.getThreadPoolExecutor());
    }
}
