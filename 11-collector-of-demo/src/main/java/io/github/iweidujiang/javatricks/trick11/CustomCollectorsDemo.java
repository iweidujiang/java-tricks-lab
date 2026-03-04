package io.github.iweidujiang.javatricks.trick11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * 自定义 collectors 演示
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/4
 */
public class CustomCollectorsDemo {

    // ===== 1. 收集到不可变列表 =====
    public static <T> Collector<T, ArrayList<T>, List<T>> toImmutableList() {
        return Collector.of(
                ArrayList::new,
                ArrayList::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                Collections::unmodifiableList
        );
    }

    // ===== 2. 按固定大小分页 =====
    public static <T> Collector<T, ?, List<List<T>>> toPages(int pageSize) {
        if (pageSize <= 0) throw new IllegalArgumentException("Page size must be positive");
        return Collector.of(
                ArrayList::new,
                (pages, item) -> {
                    if (pages.isEmpty() || pages.get(pages.size() - 1).size() >= pageSize) {
                        pages.add(new ArrayList<>());
                    }
                    pages.get(pages.size() - 1).add(item);
                },
                (left, right) -> {
                    if (left.isEmpty()) return right;
                    if (right.isEmpty()) return left;

                    List<T> lastPage = left.get(left.size() - 1);
                    if (lastPage.size() < pageSize) {
                        List<T> firstPageRight = right.get(0);
                        int space = pageSize - lastPage.size();
                        int take = Math.min(space, firstPageRight.size());
                        lastPage.addAll(firstPageRight.subList(0, take));

                        if (take < firstPageRight.size()) {
                            List<T> remaining = new ArrayList<>(firstPageRight.subList(take, firstPageRight.size()));
                            List<List<T>> merged = new ArrayList<>(left);
                            merged.add(remaining);
                            merged.addAll(right.subList(1, right.size()));
                            return merged;
                        } else {
                            left.addAll(right.subList(1, right.size()));
                            return left;
                        }
                    } else {
                        left.addAll(right);
                        return left;
                    }
                }
        );
    }

    // ===== 3. 带引号的字符串拼接 =====
    public static Collector<String, ?, String> quoteJoining(String delimiter) {
        return Collector.of(
                StringBuilder::new,
                (sb, item) -> {
                    if (sb.length() > 0) sb.append(delimiter);
                    sb.append('"').append(item).append('"');
                },
                (left, right) -> {
                    if (left.length() == 0) return right;
                    if (right.length() == 0) return left;
                    return left.append(delimiter).append(right);
                },
                StringBuilder::toString
        );
    }

    // ===== 4. 自定义统计对象 =====
    public static final class Stats {
        public final int min;
        public final int max;
        public final long count;
        public Stats(int min, int max, long count) {
            this.min = min;
            this.max = max;
            this.count = count;
        }
        @Override
        public String toString() {
            return String.format("Stats{min=%d, max=%d, count=%d}", min, max, count);
        }
    }

    public static Collector<Integer, ?, Stats> statsCollector() {
        return Collector.of(
                () -> new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0},
                (acc, value) -> {
                    acc[0] = Math.min(acc[0], value);
                    acc[1] = Math.max(acc[1], value);
                    acc[2]++;
                },
                (a1, a2) -> new int[]{
                        Math.min(a1[0], a2[0]),
                        Math.max(a1[1], a2[1]),
                        a1[2] + a2[2]
                },
                acc -> new Stats(acc[0], acc[1], acc[2])
        );
    }

    // ===== 主方法：运行所有示例 =====
    public static void main(String[] args) {
        System.out.println("=== 1. 不可变列表 ===");
        List<String> immutable = Stream.of("apple", "banana", "cherry")
                .collect(toImmutableList());
        System.out.println(immutable);
        // immutable.add("date"); // 取消注释会抛出 UnsupportedOperationException

        System.out.println("\n=== 2. 分页收集器 (每页3个) ===");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<List<Integer>> pages = numbers.stream().collect(toPages(3));
        pages.forEach(System.out::println);

        System.out.println("\n=== 3. 带引号拼接 ===");
        String quoted = Stream.of("x", "y", "z")
                .collect(quoteJoining(" | "));
        System.out.println(quoted); // "x" | "y" | "z"

        System.out.println("\n=== 4. 自定义统计 ===");
        Stats stats = Stream.of(10, 5, 20, 3, 15)
                .collect(statsCollector());
        System.out.println(stats); // Stats{min=3, max=20, count=5}

        System.out.println("\n=== 并行流测试（分页）===");
        List<List<Integer>> parallelPages = Stream.iterate(1, i -> i + 1)
                .limit(100)
                .parallel()
                .collect(toPages(7));
        System.out.println("并行分页完成，共 " + parallelPages.size() + " 页");
        System.out.println("最后一页大小: " + parallelPages.get(parallelPages.size() - 1).size());
    }
}
