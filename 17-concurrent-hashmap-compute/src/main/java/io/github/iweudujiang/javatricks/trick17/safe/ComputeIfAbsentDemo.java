package io.github.iweudujiang.javatricks.trick17.safe;

import io.github.iweudujiang.javatricks.trick17.model.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 使用 computeIfAbsent 的正确示例
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/12
 */
public class ComputeIfAbsentDemo {
    private final ConcurrentHashMap<Long, User> cache = new ConcurrentHashMap<>();

    public User getUser(Long id) {
        return cache.computeIfAbsent(id, this::loadFromDB);
    }

    private User loadFromDB(Long id) {
        // 模拟数据库加载
        return new User(id, "name" + id);
    }

    public static void main(String[] args) throws InterruptedException {
        ComputeIfAbsentDemo demo = new ComputeIfAbsentDemo();
        int threadCount = 10;
        CountDownLatch latch = new CountDownLatch(threadCount);
        long start = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                demo.getUser(1L);
                latch.countDown();
            }).start();
        }

        latch.await();
        long duration = System.currentTimeMillis() - start;
        System.out.println("ComputeIfAbsentDemo 总耗时: " + duration + " ms");
        System.out.println("缓存大小: " + demo.cache.size()); // 应为1
    }
}
