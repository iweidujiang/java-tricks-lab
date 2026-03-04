package io.github.iweidujiang.javatricks.trick12;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.StructuredTaskScope;

/**
 * 结构化并发 演示
 * <p>
 * 编译: javac --enable-preview --source 21 StructuredConcurrencyDemo.java
 * <p>
 * 运行: java --enable-preview StructuredConcurrencyDemo
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/4
 */
public class StructuredConcurrencyDemo {

    // ===== 竞速模式：取最快成功结果 =====
    public static String raceExample() throws Exception {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            scope.fork(() -> { Thread.sleep(1000); return "Slow"; });
            scope.fork(() -> { Thread.sleep(300);  return "Fast"; });
            return scope.joinUntil(Instant.now().plusSeconds(2)).result();
        }
    }

    // ===== 聚合模式：全部成功才返回 =====
    public static List<String> allOrNothingExample() throws Exception {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var f1 = scope.fork(() -> { Thread.sleep(200); return "Part1"; });
            var f2 = scope.fork(() -> { Thread.sleep(300); return "Part2"; });
            scope.join();
            return List.of(f1.get(), f2.get());
        }
    }

    // ===== 主方法 =====
    public static void main(String[] args) throws Exception {
        System.out.println("=== 竞速模式 ===");
        System.out.println("Result: " + raceExample()); // Fast Result

        System.out.println("\n=== 聚合模式 ===");
        System.out.println("Result: " + allOrNothingExample()); // [Part1, Part2]

        System.out.println("\n✅ 所有任务已自动清理，无泄漏！");
    }

}
