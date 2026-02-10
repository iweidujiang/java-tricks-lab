package io.github.iweidujiang.javatricks.trick6;

/**
 * null 安全性验证：证明 instanceof 模式匹配天然防御 NPE
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/10
 */
public class NullSafetyDemo {
    public static void run() {
        System.out.println("=== null 安全 ===");

        // 测试 null 输入
        processInput(null);

        // 测试非 null 字符串
        processInput("Hello");
    }

    public static void processInput(String input) {
        // 关键点：即使 input 为 null，instanceof 也不会抛异常
        if (input instanceof String s) {
            // 此 block 仅在 input != null 且是 String 时执行
            System.out.println("有效输入: " + s.toUpperCase());
        } else {
            System.out.println("输入为空或非字符串");
            // 当 input == null 时，走这里
        }
    }
}
