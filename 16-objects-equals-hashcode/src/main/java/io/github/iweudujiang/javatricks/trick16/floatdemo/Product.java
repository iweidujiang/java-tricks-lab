package io.github.iweudujiang.javatricks.trick16.floatdemo;

import java.util.Objects;

/**
 * 演示 float/double 字段的正确处理方式
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/11
 */
public class Product {
    private String name;
    private double price;   // 浮点字段

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        // 使用 Double.compare 保证与 hashCode 一致
        return Double.compare(product.price, price) == 0 &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        // Objects.hash 内部会调用 Double.hashCode，符合规范
        return Objects.hash(name, price);
    }
}
