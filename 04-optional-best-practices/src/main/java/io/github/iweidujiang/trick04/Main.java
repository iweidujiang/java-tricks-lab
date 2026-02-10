package io.github.iweidujiang.trick04;

import java.util.Optional;

/**
 * Optional 正确使用方式演示
 * 包含：链式操作、JDK 9+ 新特性、最佳实践
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/10 14:38
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== 1. 链式安全取值 ===");
        User user = new User("苏渡苇", 25);
        user.setProfile(new Profile(new Contact("iweidujiang@163.com", "北京")));

        String city = Optional.ofNullable(user)
                .map(User::getProfile)
                .map(Profile::getContact)
                .map(Contact::getCity)
                .orElse("未知城市");
        System.out.println("城市: " + city);

        System.out.println("\n=== 2. JDK 9+：ifPresentOrElse ===");
        Optional<String> opt = Optional.of("Hello");
        opt.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("值为空")
        );

        Optional<String> empty = Optional.empty();
        empty.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("值为空")
        );

        System.out.println("\n=== 3. 备选方案：or(Supplier) ===");
        Optional<String> fromCache = Optional.empty();
        Optional<String> result = fromCache.or(() -> Optional.of("从数据库查到"));
        System.out.println("结果: " + result.get());

        System.out.println("\n=== 4. 错误示范 vs 正确示范 ===");
        badUsage();
        goodUsage();
    }

    public static void badUsage() {
        System.out.println("【反面教材】");
        Optional<String> opt = getName();
        if (opt.isPresent()) { // 判空 + get，毫无意义！
            String name = opt.get();
            System.out.println("名字: " + name);
        }
    }

    public static void goodUsage() {
        System.out.println("【正面示范】");
        getName().ifPresent(name -> System.out.println("名字: " + name));
        // 或
        String name = getName().orElse("匿名");
        System.out.println("名字（带默认）: " + name);
    }

    // 模拟可能返回 null 的方法
    public static Optional<String> getName() {
        return Optional.ofNullable(Math.random() > 0.5 ? "苏渡苇" : null);
    }

    // ===== 模型类 =====

    static class User {
        private String name;
        private int age;
        private Profile profile;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public Profile getProfile() { return profile; }
        public void setProfile(Profile profile) { this.profile = profile; }
    }

    static class Profile {
        private Contact contact;
        public Profile(Contact contact) { this.contact = contact; }
        public Contact getContact() { return contact; }
    }

    static class Contact {
        private String email;
        private String city;

        public Contact(String email, String city) {
            this.email = email;
            this.city = city;
        }

        public String getEmail() { return email; }
        public String getCity() { return city; }
    }
}
