package io.github.iweidujiang.javatricks.trick7;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 复杂泛型 DTO 示例
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/25
 */
public class GenericDtoDemo {
    public static void main(String[] args) {
        // 复杂泛型：var 可避免重复声明
        var futures = fetchUserBatches(); // 推断为 List<CompletableFuture<Map<String, User>>>

        System.out.println("获取了 " + futures.size() + " 个异步任务");
    }

    private static List<CompletableFuture<Map<String, User>>> fetchUserBatches() {
        return List.of();
    }

    record User(String name) {}
}
