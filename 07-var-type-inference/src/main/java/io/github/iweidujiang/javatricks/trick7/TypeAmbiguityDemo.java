package io.github.iweidujiang.javatricks.trick7;

import java.util.ArrayList;

/**
 * 类型模糊反面教材
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/25
 */
public class TypeAmbiguityDemo {
    public static void main(String[] args) {
        // 危险：类型不明确
        var list = new ArrayList<>(); // 推断为 ArrayList<Object>
        list.add("hello");
        list.add(42); // 编译通过，但可能非预期
        list.forEach(System.out::println);

        // 正确做法：显式指定
        var stringList = new ArrayList<String>();
        stringList.add("world");
        stringList.forEach(System.out::println);
    }
}
