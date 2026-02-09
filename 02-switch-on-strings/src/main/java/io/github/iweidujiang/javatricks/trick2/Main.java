package io.github.iweidujiang.javatricks.trick2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 演示 switch 对 String 的支持原理与性能对比
 * （含 if-else / Map / switch 三路 benchmark）
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang
 * 公众号: 苏渡苇
 * @date 2026/2/9 22:03
 */
public class Main {

    // 测试用的动作列表（10 个常见操作）
    private static final String[] ACTIONS = {
            "create", "update", "delete", "view", "edit",
            "save", "load", "exit", "help", "config"
    };

    private static final int ITERATIONS = 100_000; // 外层循环次数

    public static void main(String[] args) {
        System.out.println("=== switch(String) 基础用法 ===");
        System.out.println(handleActionWithSwitch("create"));
        System.out.println(handleActionWithSwitch("update"));

        System.out.println("\n=== Switch 表达式（JDK 14+） ===");
        String msg = switch ("SUCCESS") {
            case "PENDING" -> "处理中";
            case "SUCCESS" -> {
                System.out.println("【日志】操作成功！");
                yield "搞定！";
            }
            case "FAILED" -> "失败啦";
            default -> "未知状态";
        };
        System.out.println("消息: " + msg);

        // 执行三路性能对比
        performanceTest();
    }

    /**
     * 使用 switch 表达式处理动作
     */
    public static String handleActionWithSwitch(String action) {
        return switch (action) {
            case "create" -> "执行创建";
            case "update" -> "执行更新";
            case "delete" -> "执行删除";
            case "view"   -> "查看详情";
            case "edit"   -> "进入编辑";
            case "save"   -> "保存成功";
            case "load"   -> "加载完成";
            case "exit"   -> "退出系统";
            case "help"   -> "帮助文档";
            case "config" -> "配置已应用";
            default -> "未知操作: " + action;
        };
    }

    /**
     * 使用 if-else 链处理动作
     */
    public static String handleActionWithIfElse(String action) {
        if ("create".equals(action)) {
            return "执行创建";
        } else if ("update".equals(action)) {
            return "执行更新";
        } else if ("delete".equals(action)) {
            return "执行删除";
        } else if ("view".equals(action)) {
            return "查看详情";
        } else if ("edit".equals(action)) {
            return "进入编辑";
        } else if ("save".equals(action)) {
            return "保存成功";
        } else if ("load".equals(action)) {
            return "加载完成";
        } else if ("exit".equals(action)) {
            return "退出系统";
        } else if ("help".equals(action)) {
            return "帮助文档";
        } else if ("config".equals(action)) {
            return "配置已应用";
        } else {
            return "未知操作: " + action;
        }
    }

    /**
     * 使用 Map 预加载处理动作
     */
    private static final Map<String, String> ACTION_MAP = new HashMap<>();
    static {
        ACTION_MAP.put("create", "执行创建");
        ACTION_MAP.put("update", "执行更新");
        ACTION_MAP.put("delete", "执行删除");
        ACTION_MAP.put("view", "查看详情");
        ACTION_MAP.put("edit", "进入编辑");
        ACTION_MAP.put("save", "保存成功");
        ACTION_MAP.put("load", "加载完成");
        ACTION_MAP.put("exit", "退出系统");
        ACTION_MAP.put("help", "帮助文档");
        ACTION_MAP.put("config", "配置已应用");
    }
    public static String handleActionWithMap(String action) {
        return ACTION_MAP.getOrDefault(action, "未知操作: " + action);
    }

    /**
     * 三路性能对比（简化版，非 JMH，仅作趋势参考）
     */
    public static void performanceTest() {
        System.out.println("\n=== 简易性能对比（" + ITERATIONS + " 次 × " + ACTIONS.length + " 分支）===");

        // Warm up（预热 JIT）
        for (int i = 0; i < 10_000; i++) {
            for (String action : ACTIONS) {
                handleActionWithSwitch(action);
                handleActionWithIfElse(action);
                handleActionWithMap(action);
            }
        }

        // 测试 switch
        long start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            for (String action : ACTIONS) {
                handleActionWithSwitch(action);
            }
        }
        long switchTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

        // 测试 if-else
        start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            for (String action : ACTIONS) {
                handleActionWithIfElse(action);
            }
        }
        long ifElseTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

        // 测试 Map
        start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            for (String action : ACTIONS) {
                handleActionWithMap(action);
            }
        }
        long mapTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

        System.out.println("- switch(String): " + switchTime + " ms");
        System.out.println("- if-else chain:  " + ifElseTime + " ms");
        System.out.println("- Map.get():      " + mapTime + " ms");
        System.out.println("（注：真实性能请使用 JMH 进行严谨基准测试）");
    }
}
