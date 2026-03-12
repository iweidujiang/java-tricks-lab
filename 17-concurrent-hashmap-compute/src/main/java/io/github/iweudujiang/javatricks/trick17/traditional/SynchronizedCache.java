package io.github.iweudujiang.javatricks.trick17.traditional;

import io.github.iweudujiang.javatricks.trick17.model.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 加锁方式，虽然安全但并发度低
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/12
 */
public class SynchronizedCache {
    private final ConcurrentHashMap<Long, User> cache = new ConcurrentHashMap<>();

    public synchronized User getUser(Long id) {
        User user = cache.get(id);
        if (user == null) {
            user = loadFromDB(id);
            cache.put(id, user);
        }
        return user;
    }

    private User loadFromDB(Long id) {
        // 模拟数据库加载
        return new User(id, "name" + id);
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedCache cache = new SynchronizedCache();
        int threadCount = 10;
        CountDownLatch latch = new CountDownLatch(threadCount);
        long start = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                cache.getUser(1L);
                latch.countDown();
            }).start();
        }

        latch.await();
        long duration = System.currentTimeMillis() - start;
        System.out.println("SynchronizedCache 总耗时: " + duration + " ms");
        System.out.println("缓存大小: " + cache.cache.size()); // 应为1
    }
}
