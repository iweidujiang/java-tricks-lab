package io.github.iweudujiang.javatricks.trick17.benchmark;

import io.github.iweudujiang.javatricks.trick17.model.User;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * JMH 测试
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/12
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MapInitBenchmark {

    private ConcurrentHashMap<Long, User> map;
    private Long key = 1L;

    @Setup(Level.Iteration)
    public void setup() {
        map = new ConcurrentHashMap<>();
    }

    // 错误方案：get+put
    @Benchmark
    public User unsafeGetPut(Blackhole bh) {
        User user = map.get(key);
        if (user == null) {
            user = new User(key, "name");
            map.put(key, user);
        }
        return user;
    }

    // synchronized 方案
    @Benchmark
    public synchronized User synchronizedGetPut(Blackhole bh) {
        User user = map.get(key);
        if (user == null) {
            user = new User(key, "name");
            map.put(key, user);
        }
        return user;
    }

    // putIfAbsent 方案（先计算后插入）
    @Benchmark
    public User putIfAbsent(Blackhole bh) {
        User user = map.get(key);
        if (user == null) {
            user = new User(key, "name");
            User existing = map.putIfAbsent(key, user);
            if (existing != null) {
                user = existing;
            }
        }
        return user;
    }

    // computeIfAbsent 方案
    @Benchmark
    public User computeIfAbsent(Blackhole bh) {
        return map.computeIfAbsent(key, k -> new User(k, "name"));
    }
}
