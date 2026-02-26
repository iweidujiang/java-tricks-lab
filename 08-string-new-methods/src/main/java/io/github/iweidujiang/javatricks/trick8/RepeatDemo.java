package io.github.iweidujiang.javatricks.trick8;

/**
 * repeat() 示例
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/26
 */
public class RepeatDemo {

    public static void main(String[] args) {
        String separator = "-".repeat(30);
        String indent = "  ".repeat(3);

        System.out.println(separator);
        System.out.println(indent + "Hello, JDK 11!");
        System.out.println(separator);
    }
}
