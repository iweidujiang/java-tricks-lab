package io.github.iweidujiang.javatricks.trick7;

import java.util.List;

/**
 * for-each 中使用 var 的边界
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/25
 */
public class ForEachDemo {
    public static void main(String[] args) {
        List<String> items = List.of("a", "b", "c");

        // 可接受：类型明确
        for (var item : items) {
            System.out.println(item);
        }

        // 警告：如果 items 是 List<?>，item 类型不明
        // 建议写成：for (String item : items)
    }
}
