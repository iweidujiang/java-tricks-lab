package io.github.iweudujiang.javatricks.trick16.benchmark;

import io.github.iweudujiang.javatricks.trick16.objectsstyle.UserObjects;
import io.github.iweudujiang.javatricks.trick16.traditional.UserTraditional;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * 性能基准测试
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/11
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class EqualsHashCodeBenchmark {
    private UserTraditional manualUser;
    private UserObjects objectsUser;

    @Setup
    public void setup() {
        manualUser = new UserTraditional("Alice", 30);
        objectsUser = new UserObjects("Alice", 30);
    }

    @Benchmark
    public int manualHashCode() {
        return manualUser.hashCode();
    }

    @Benchmark
    public int objectsHashCode() {
        return objectsUser.hashCode();
    }

    @Benchmark
    public boolean manualEquals() {
        return manualUser.equals(manualUser);
    }

    @Benchmark
    public boolean objectsEquals() {
        return objectsUser.equals(objectsUser);
    }
}
