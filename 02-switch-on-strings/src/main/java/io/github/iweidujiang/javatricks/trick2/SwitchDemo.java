package io.github.iweidujiang.javatricks.trick2;

/**
 * Switch 测试类
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang
 * 公众号: 苏渡苇
 * @date 2026/2/9 22:15
 */
public class SwitchDemo {

    public static int getValue(String s) {
        switch (s) {
            case "apple":  return 1;
            case "banana": return 2;
            case "cherry": return 3;
            default:       return -1;
        }
    }
}
