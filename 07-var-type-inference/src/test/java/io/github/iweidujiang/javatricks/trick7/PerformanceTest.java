package io.github.iweidujiang.javatricks.trick7;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 性能测试：var vs 显式类型
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/25
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, warmups = 1)        // 至少 1 轮预热
@Warmup(iterations = 3, time = 1)    // 预热 3 轮，每轮 1 秒
@Measurement(iterations = 5, time = 1) // 测量 5 轮，每轮 1 秒
public class PerformanceTest {

    private static final int SIZE = 1_000;

    @Benchmark
    public void testVar(Blackhole blackhole) {
        var list = new ArrayList<String>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            list.add("item-" + i);
        }
        blackhole.consume(list); // 防止 DCE
    }

    @Benchmark
    public void testExplicit(Blackhole blackhole) {
        ArrayList<String> list = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            list.add("item-" + i);
        }
        blackhole.consume(list); // 防止 DCE
    }
}
