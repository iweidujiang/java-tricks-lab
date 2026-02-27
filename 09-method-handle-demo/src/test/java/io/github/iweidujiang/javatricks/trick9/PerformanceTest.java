package io.github.iweidujiang.javatricks.trick9;

import org.openjdk.jmh.annotations.*;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 性能测试
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/27
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class PerformanceTest {
    private Person person = new Person("Test");
    private Method reflectMethod;
    private MethodHandle mh;

    @Setup
    public void setup() throws Exception {
        reflectMethod = Person.class.getMethod("getName");
        mh = MethodHandles.lookup()
                .findVirtual(Person.class, "getName", MethodType.methodType(String.class));
    }

    @Benchmark
    public String testDirect() {
        return person.getName();
    }

    @Benchmark
    public String testReflection() throws Exception {
        return (String) reflectMethod.invoke(person);
    }

    @Benchmark
    public String testMethodHandle() throws Throwable {
        return (String) mh.invokeExact(person);
    }
}
