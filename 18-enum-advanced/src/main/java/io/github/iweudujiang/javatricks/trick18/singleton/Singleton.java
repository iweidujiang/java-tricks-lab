package io.github.iweudujiang.javatricks.trick18.singleton;

/**
 * 枚举实现单例模式
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/12
 */
public enum Singleton {
    INSTANCE;

    private String data = "初始数据";

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void doSomething() {
        System.out.println("执行单例方法，当前数据: " + data);
    }
}
