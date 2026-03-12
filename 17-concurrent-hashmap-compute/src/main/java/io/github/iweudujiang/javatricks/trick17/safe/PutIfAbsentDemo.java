package io.github.iweudujiang.javatricks.trick17.safe;

import io.github.iweudujiang.javatricks.trick17.model.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 使用 putIfAbsent 的示例（需要提前计算值）
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/12
 */
public class PutIfAbsentDemo {
    private final ConcurrentHashMap<Long, User> cache = new ConcurrentHashMap<>();

    public User getUser(Long id) {
        // 先尝试获取
        User user = cache.get(id);
        if (user == null) {
            // 计算值（这里仍然可能被多个线程重复计算）
            user = loadFromDB(id);
            User existing = cache.putIfAbsent(id, user);
            if (existing != null) {
                user = existing; // 如果其他线程已经放入，使用已有的
            }
        }
        return user;
    }

    private User loadFromDB(Long id) {
        return new User(id, "name" + id);
    }

    public static void main(String[] args) throws InterruptedException {
        PutIfAbsentDemo demo = new PutIfAbsentDemo();
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
        System.out.println("PutIfAbsentDemo 总耗时: " + duration + " ms");
        System.out.println("缓存大小: " + demo.cache.size()); // 应为1
    }
}
