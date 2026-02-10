package io.github.iweidujiang.javatricks.trick05;

import io.github.iweidujiang.javatricks.trick05.orders.*;
import io.github.iweidujiang.javatricks.trick05.shapes.Circle;
import io.github.iweidujiang.javatricks.trick05.shapes.Rectangle;
import io.github.iweidujiang.javatricks.trick05.shapes.ShapeCalculator;
import io.github.iweidujiang.javatricks.trick05.shapes.Triangle;

/**
 * 演示主类
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/10 15:24
 */
public class Main {
    public static void main(String[] args) {
        // 演示形状计算
        System.out.println("=== 形状面积 ===");
        System.out.printf("圆: %.2f%n", ShapeCalculator.area(new Circle(2.0)));
        System.out.printf("矩形: %.2f%n", ShapeCalculator.area(new Rectangle(3, 4)));
        System.out.printf("三角形: %.2f%n", ShapeCalculator.area(new Triangle(3, 4, 5)));

        System.out.println("\n=== 订单状态 ===");
        System.out.println(OrderStatusHandler.getMessage(new Pending()));
        System.out.println(OrderStatusHandler.getMessage(new Confirmed("2026-02-10")));
        System.out.println(OrderStatusHandler.getMessage(new Shipped("SF123")));
        System.out.println(OrderStatusHandler.getMessage(new Cancelled("用户取消")));
    }
}
