package io.github.iweidujiang.javatricks.trick8;

/**
 * isBlank() 示例
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/26
 */
public class IsBlankDemo {

    public static void main(String[] args) {
        String[] inputs = {"", "   ", "\t\n", "hello", null};

        for (String s : inputs) {
            // 注意：s 可能为 null
            boolean blank = s == null || s.isBlank();
            System.out.println("[" + s + "] isBlank? " + blank);
        }
    }
}
