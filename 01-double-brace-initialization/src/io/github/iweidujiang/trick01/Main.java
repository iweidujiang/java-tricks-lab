package io.github.iweidujiang.trick01;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 双大括号初始化 vs List.of() 对比示例
 * 必须在非静态上下文中才能复现 this$0 引用
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang
 * 公众号: 苏渡苇
 * @date 2026/2/9 17:19
 */
public class Main {

    /**
     * 非静态方法：触发匿名内部类持有外部实例引用
     */
    public void testDoubleBraceInNonStaticContext() {
        List<String> dangerousList = new ArrayList<>() {{
            add("苏");
            add("渡");
            add("苇");
        }};
        inspectOuterReference(dangerousList);
    }

    private void inspectOuterReference(List<?> list) {
        System.out.println("【双大括号列表】");
        System.out.println("- 类型: " + list.getClass());

        try {
            Field outerRef = list.getClass().getDeclaredField("this$0");
            outerRef.setAccessible(true);
            Object outer = outerRef.get(list);
            if (outer != null) {
                System.out.println("- 持有外部实例引用: 是！→ " + outer.getClass().getName());
            } else {
                System.out.println("- this$0 存在但为 null");
            }
        } catch (NoSuchFieldException e) {
            System.out.println("- 未发现外部引用字段（可能在静态上下文中）");
        } catch (IllegalAccessException e) {
            System.out.println("- 无法访问 this$0: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 在非静态上下文中测试双大括号初始化 ===");
        new Main().testDoubleBraceInNonStaticContext();

        System.out.println("\n=== 安全替代方案：List.of() ===");
        List<String> safeList = List.of("苏", "渡", "苇");
        System.out.println("- 类型: " + safeList.getClass());
        System.out.println("- 无匿名类，无外部引用，不可变，安全！");
    }
}
