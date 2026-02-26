package io.github.iweidujiang.javatricks.trick8;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * 性能测试
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/26
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class PerformanceTest {

    private static final String TEXT_WITH_SPACES = "   \t\n  ";
    private static final String MULTI_LINE = "line1\nline2\r\nline3\rline4";

    // === isBlank() vs trim().isEmpty() ===
    @Benchmark
    public boolean testIsBlank() {
        return TEXT_WITH_SPACES.isBlank();
    }

    @Benchmark
    public boolean testTrimIsEmpty() {
        return TEXT_WITH_SPACES != null && TEXT_WITH_SPACES.trim().isEmpty();
    }

    // === repeat() vs StringBuilder ===
    @Benchmark
    public String testRepeat() {
        return "-".repeat(100);
    }

    @Benchmark
    public String testStringBuilder() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) sb.append('-');
        return sb.toString();
    }

    // === lines() vs split ===
    @Benchmark
    public long testLines(Blackhole bh) {
        return MULTI_LINE.lines().count(); // 触发流处理
    }

    @Benchmark
    public long testSplit(Blackhole bh) {
        return MULTI_LINE.split("\\r?\\n").length;
    }
}
