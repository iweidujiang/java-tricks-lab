package io.github.iweidujiang.trick03;

import java.io.FileInputStream;

/**
 * try-with-resources DEMO
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/10 11:00
 */
public class TryWithResDemo {

    public static void main(String[] args) throws Exception {
        try (FileInputStream fis = new FileInputStream("E:/test.text")) {
            System.out.println("读取中...");
        }
    }
}
