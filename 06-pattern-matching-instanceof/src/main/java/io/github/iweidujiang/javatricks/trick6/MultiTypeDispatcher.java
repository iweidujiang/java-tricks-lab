package io.github.iweidujiang.javatricks.trick6;

import java.util.List;

/**
 * 多类型分发示例：展示如何用 instanceof 模式匹配替代 if-else 链
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/10
 */
public class MultiTypeDispatcher {
    public static void run() {
        System.out.println("=== 多类型分发 ===");

        dispatch("文本");
        dispatch(42);
        dispatch(List.of(1, 2, 3));
        dispatch(new Object());
    }

    public static void dispatch(Object obj) {
        if (obj instanceof String s) {
            System.out.println("处理: " + s);
            // → 处理: 文本
        } else if (obj instanceof Integer i) {
            System.out.println("处理: " + i);
            // → 处理: 42
        } else if (obj instanceof List<?> list) {
            System.out.println("处理: 列表大小=" + list.size());
            // → 处理: 列表大小=3
        } else {
            System.out.println("处理: 未知类型 (" + obj.getClass().getSimpleName() + ")");
            // → 处理: 未知类型 (Object)
        }
    }
}
