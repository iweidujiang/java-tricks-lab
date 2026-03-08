package io.github.iweidujiang.javatricks.trick14;

import java.util.function.Supplier;

/**
 * 手动包装工具
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/8
 */
public class ContextWrapper {
    // 包装 Runnable
    public static Runnable wrap(ContextStrategy context, Runnable task) {
        String userId = context.getUserId();
        return () -> {
            try {
                if (userId != null) {
                    context.setUserId(userId);
                }
                task.run();
            } finally {
                context.clear();
            }
        };
    }

    // 包装 Supplier（用于 supplyAsync）
    public static <T> Supplier<T> wrap(ContextStrategy context, Supplier<T> task) {
        String userId = context.getUserId();
        return () -> {
            try {
                if (userId != null) {
                    context.setUserId(userId);
                }
                return task.get();
            } finally {
                context.clear();
            }
        };
    }
}
