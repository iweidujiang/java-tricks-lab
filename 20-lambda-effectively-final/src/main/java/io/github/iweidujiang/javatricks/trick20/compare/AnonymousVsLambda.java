package io.github.iweidujiang.javatricks.trick20.compare;

/**
 * 匿名内部类对比
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/14
 */
public class AnonymousVsLambda {
    public static void main(String[] args) {
        int localVar = 42;

        // Lambda 表达式
        Runnable lambda = () -> System.out.println("Lambda: " + localVar);

        // 匿名内部类
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous: " + localVar);
            }
        };

        lambda.run();
        anonymous.run();

//         两者都要求 localVar 是 effectively final
//         localVar = 100; // 取消注释将导致编译错误
    }
}
