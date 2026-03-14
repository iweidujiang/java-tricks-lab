package io.github.iweidujiang.javatricks.trick20.loop;

import java.util.ArrayList;
import java.util.List;

/**
 * 循环中的 Lambda 陷阱
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/14
 */
public class LoopLambdaDemo {
    public static void main(String[] args) {
        List<Runnable> tasks = new ArrayList<>();

        // 错误：直接在 Lambda 中使用循环变量 i（编译错误）
        // for (int i = 0; i < 10; i++) {
        //     tasks.add(() -> System.out.println(i));
        // }

        // 正确：创建临时变量 j 捕获当前值
        for (int i = 0; i < 10; i++) {
            int j = i; // j 是 effectively final
            tasks.add(() -> System.out.println(j));
        }

        // 执行所有任务
        tasks.forEach(Runnable::run);
    }
}
