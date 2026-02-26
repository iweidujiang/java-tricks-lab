package io.github.iweidujiang.javatricks.trick8;

import java.util.List;

/**
 * 组合实战
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/26
 */
public class UserInputCleaner {

    public static void main(String[] args) {
        String userInput = """
            # 注释行
            
            苹果
              香蕉  
            # 另一个注释
            橙子
            """;

        List<String> cleanLines = userInput.lines()
                .map(String::strip)
                .filter(line -> !line.isBlank())
                .filter(line -> !line.startsWith("#"))
                .toList();

        System.out.println("清洗结果: " + cleanLines);
        // 输出：清洗结果: [苹果, 香蕉, 橙子]
    }
}
