package io.github.iweidujiang.javatricks.trick7;

import java.net.http.HttpRequest;
import java.time.Duration;

/**
 * Builder 模式示例
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/25
 */
public class BuilderDemo {

    public static void main(String[] args) {
        // 使用 var 让配置过程更清晰
        var request = HttpRequest.newBuilder()
                .uri(java.net.URI.create("https://api.example.com"))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        System.out.println("请求已构建");
    }
}
