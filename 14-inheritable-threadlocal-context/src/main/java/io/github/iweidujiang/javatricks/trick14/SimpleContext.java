package io.github.iweidujiang.javatricks.trick14;

/**
 * 普通 ThreadLocal
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/8
 */
public class SimpleContext implements ContextStrategy {
    private static final ThreadLocal<String> USER_ID = new ThreadLocal<>();

    @Override
    public void setUserId(String userId) {
        USER_ID.set(userId);
    }

    @Override
    public String getUserId() {
        return USER_ID.get();
    }

    @Override
    public void clear() {
        USER_ID.remove();
    }
}
