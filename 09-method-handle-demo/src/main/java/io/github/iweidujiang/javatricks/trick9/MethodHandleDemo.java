package io.github.iweidujiang.javatricks.trick9;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * MethodHandle 反射调用示例
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/27
 */
public class MethodHandleDemo {

    public static void main(String[] args) throws Throwable {
        Person p = new Person("苏渡苇");

        // 获取 MethodHandle
        MethodHandle mh = MethodHandles.lookup()
                .findVirtual(Person.class, "getName", MethodType.methodType(String.class));

        // invokeExact：参数类型必须完全匹配
        String name = (String) mh.invokeExact(p);

        System.out.println("MethodHandle 调用结果: " + name);

        // 演示字段读取（私有字段）
        MethodHandle getter = MethodHandles.lookup()
                .findGetter(Person.class, "name", String.class);
        String fieldName = (String) getter.invokeExact(p);
        System.out.println("私有字段读取: " + fieldName);
    }
}
