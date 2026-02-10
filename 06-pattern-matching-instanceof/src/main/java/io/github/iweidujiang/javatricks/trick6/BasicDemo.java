package io.github.iweidujiang.javatricks.trick6;

/**
 * 基础用法演示
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/10
 */
public class BasicDemo {

    public static void run() {
        System.out.println("=== 基础用法 ===");
        Object obj = "Hello";

        if (obj instanceof String s) {
            System.out.println("字符串内容: " + s);
            // → 字符串内容: Hello
        }

        obj = 123;
        if (obj instanceof Integer i) {
            System.out.println("整数值: " + i);
            // → 整数值: 123
        }
    }
}
