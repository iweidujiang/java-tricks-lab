package io.github.iweidujiang.javatricks.trick14;

/**
 * 上下文策略接口
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/8
 */
public interface ContextStrategy {
    void setUserId(String userId);
    String getUserId();
    void clear();
}
