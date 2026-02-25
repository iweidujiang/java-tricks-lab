package io.github.iweidujiang.javatricks.trick7;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream 链式调用示例
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/25
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "", "Charlie");

        // 使用 var 提升可读性
        var filteredNames = names.stream()
                .filter(s -> !s.isEmpty())
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(filteredNames); // [ALICE, BOB, CHARLIE]
    }
}
