package io.github.iweidujiang.javatricks.trick8;

/**
 * lines() 示例
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/26
 */
public class LinesDemo {

    public static void main(String[] args) {
        String text = "Unix\nWindows\r\nOldMac\rEnd";

        System.out.println("原始文本: " + text.replace("\n", "\\n").replace("\r", "\\r"));

        var lines = text.lines().toList();
        System.out.println("分割结果: " + lines);
        lines.forEach(System.out::println);
    }
}
