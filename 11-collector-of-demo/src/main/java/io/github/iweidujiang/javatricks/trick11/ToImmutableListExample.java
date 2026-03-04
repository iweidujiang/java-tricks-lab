package io.github.iweidujiang.javatricks.trick11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Colletor.of 演示
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/4
 */
public class ToImmutableListExample {
    public static void main(String[] args) {
        Collector<String, List<String>, List<String>> toImmutable = Collector.of(
                ArrayList::new,
                List::add,
                (l, r) -> { l.addAll(r); return l; },
                Collections::unmodifiableList
        );

        List<String> result = Stream.of("a", "b", "c")
                .collect(toImmutable);

        System.out.println(result); // [a, b, c]
        // result.add("d"); // UnsupportedOperationException
    }
}
