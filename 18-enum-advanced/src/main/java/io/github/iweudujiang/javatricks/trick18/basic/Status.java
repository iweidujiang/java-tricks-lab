package io.github.iweudujiang.javatricks.trick18.basic;

/**
 * 带字段和方法的枚举
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/12
 */
public enum Status {
    PENDING(0, "待处理"),
    PROCESSING(1, "处理中"),
    COMPLETED(2, "已完成"),
    CANCELLED(3, "已取消");

    private final int code;
    private final String description;

    Status(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() { return code; }
    public String getDescription() { return description; }

    public static Status fromCode(int code) {
        for (Status s : values()) {
            if (s.code == code) return s;
        }
        throw new IllegalArgumentException("未知状态码: " + code);
    }
}
