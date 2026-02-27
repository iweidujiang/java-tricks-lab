package io.github.iweidujiang.javatricks.trick9;

import java.lang.reflect.Method;

/**
 * 传统反射 DEMO
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/27
 */
public class ReflectionDemo {

    public static void main(String[] args) throws Exception {
        Person p = new Person("苏渡苇");

        // 传统反射调用
        Method method = Person.class.getMethod("getName");
        String name = (String) method.invoke(p);

        System.out.println("反射调用结果: " + name);
    }
}
