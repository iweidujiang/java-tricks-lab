package io.github.iweidujiang.trick03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * try-with-resources 高级用法演示
 * 包含：异常抑制、JDK 9+ 用法、自定义 AutoCloseable
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 *
 * @date 2026/2/10 10:44
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("=== 1. 基础用法：自动关闭文件流 ===");
        readFile();

        System.out.println("\n=== 2. 异常抑制演示 ===");
        demonstrateSuppressed();

        System.out.println("\n=== 3. JDK 9+：直接使用 effectively final 变量 ===");
        useJdk9Syntax();

        System.out.println("\n=== 4. 自定义 AutoCloseable 资源 ===");
        useTimer();
    }

    public static void readFile() {
        try (FileInputStream fis = new FileInputStream(createTempFile())) {
            System.out.println("文件长度: " + fis.available());
        } catch (IOException e) {
            System.err.println("读取失败: " + e.getMessage());
        }
    }

    public static void demonstrateSuppressed() {
        try (BadResource r = new BadResource()) {
            throw new RuntimeException("主逻辑异常");
        } catch (Exception e) {
            System.out.println("主异常: " + e.getMessage());
            for (Throwable sup : e.getSuppressed()) {
                System.out.println("被抑制异常: " + sup.getMessage());
            }
        }
    }

    public static void useJdk9Syntax() throws FileNotFoundException {
        FileInputStream fis = getStream(); // effectively final
        try (fis) { // JDK 9+ 允许
            System.out.println("JDK 9+ 用法成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void useTimer() {
        try (Timer timer = new Timer()) {
            try { Thread.sleep(30); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
            System.out.println("业务执行中...");
        }
    }

    private static FileInputStream getStream() throws FileNotFoundException {
        return new FileInputStream(createTempFile());
    }

    private static File createTempFile() {
        try {
            File f = File.createTempFile("demo", ".txt");
            f.deleteOnExit();
            return f;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ===== 辅助类 =====

    static class BadResource implements AutoCloseable {
        @Override
        public void close() throws Exception {
            throw new RuntimeException("close() 也炸了！");
        }
    }

    static class Timer implements AutoCloseable {
        private final long start = System.nanoTime();
        @Override
        public void close() {
            long ms = (System.nanoTime() - start) / 1_000_000;
            System.out.println("【Timer】耗时: " + ms + " ms");
        }
    }
}
