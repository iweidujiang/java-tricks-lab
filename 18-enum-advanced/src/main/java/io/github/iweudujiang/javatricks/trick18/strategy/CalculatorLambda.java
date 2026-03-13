package io.github.iweudujiang.javatricks.trick18.strategy;

/**
 * 枚举+Lambda
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/12
 */
public enum CalculatorLambda {
//    ADD((a, b) -> a + b),
    ADD(Integer::sum),
    SUBTRACT((a, b) -> a - b),
    MULTIPLY((a, b) -> a * b),
    DIVIDE((a, b) -> a / b);

    private final Operation operation;

    CalculatorLambda(Operation operation) {
        this.operation = operation;
    }

    public int apply(int a, int b) {
        return operation.apply(a, b);
    }

    @FunctionalInterface
    public interface Operation {
        int apply(int a, int b);
    }
}
