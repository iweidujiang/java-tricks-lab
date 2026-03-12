package io.github.iweudujiang.javatricks.trick17.traditional;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示传统 get+put 在多线程下的非原子性问题
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/12
 */
public class UnsafeGetPutDemo {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        int threadCount = 10;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(threadCount);
        AtomicInteger successCount = new AtomicInteger(0); // 记录成功插入的线程数

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    startLatch.await(); // 等待统一开始信号
                    Integer value = map.get("key");
                    if (value == null) {
                        // 模拟计算耗时，扩大冲突窗口
                        Thread.sleep(1);
                        value = 1;
                        map.put("key", value);
                        System.out.println(Thread.currentThread().getName() + " 执行了 put");
                        successCount.incrementAndGet();
                    } else {
                        System.out.println(Thread.currentThread().getName() + " 直接获取到值: " + value);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endLatch.countDown();
                }
            }).start();
        }

        System.out.println("所有线程已就绪，3 秒后同时开始...");
        Thread.sleep(3000);
        startLatch.countDown(); // 统一发令枪
        endLatch.await();

        System.out.println("最终 map 中的值: " + map.get("key"));
        System.out.println("实际执行 put 的线程数: " + successCount.get() + " (期望为 1)");
    }
}
