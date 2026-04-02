# java-tricks-lab

一个用「小而精」示例来复盘 Java 语言特性、并发与工程实践的实验室。每个目录对应一个主题（通常也是一篇文章），代码力求**可直接运行、可改动验证**。

## 环境要求

- **JDK**：优先使用 **JDK 21**（大多数模块的 `pom.xml` 以 21 作为编译目标；个别主题虽是早期特性，但也能在 21 下运行）
- **构建工具**：大多数模块是**独立 Maven 工程**（目录下有 `pom.xml`）
- **IDE（推荐）**：IntelliJ IDEA / VS Code（装好 Java 扩展）

## 如何运行

### 方式 A：用 IDE（推荐）

1. 打开仓库根目录
2. 选择你要运行的模块（例如 `10-virtual-threads-demo`）
3. 直接运行其中的 `Main`（或模块里提供的示例入口类）

### 方式 B：命令行（Maven 模块）

在任意一个**带 `pom.xml`** 的模块目录里执行：

```bash
mvn -q -DskipTests package
```

然后用 `java -cp` 运行模块里的入口类（按你的模块实际包名替换）：

```bash
java -cp target/classes io.github.iweidujiang.javatricks.trick2.Main
```

提示：
- 有些模块包含基准/性能相关代码（例如依赖 JMH），你可以优先从 `Main` 演示入口跑起。
- 如果遇到 Java 版本不匹配，请以该模块 `pom.xml` 中的 `maven.compiler.source/target` 为准。

## 目录结构

- `01-*` ~ `20-*`：按主题拆分的示例模块（多数为 Maven 单模块工程）
- `springboot-druid-safe-demo`：Spring Boot 相关示例模块

## 主题导航（代码目录 ↔ 文章）

- **01** `01-double-brace-initialization`：[双大括号（{{...}}）初始化？小心内存泄漏的“回旋踢”！](https://mp.weixin.qq.com/s/MB8n8vK9cSrzShvG95bc1A)
- **02** `02-switch-on-strings`：[switch 支持字符串？JVM 背后练了“闪电五连鞭”！](https://mp.weixin.qq.com/s/iEfyp19niL5GZOu2mW85dA)
- **03** `03-try-with-resources`：[try-with-resources 的隐藏用法：资源管理界的“自动挡”！](https://mp.weixin.qq.com/s/_jh8LQOksKHeJ7zLVLSFIA)
- **04** `04-optional-best-practices`：[Optional 的正确打开方式：不要只记得 ifPresent 了！](https://mp.weixin.qq.com/s/X9BJB6hSDw_Odpbji7st-w)
- **05** `05-record-sealed-class`：[record + sealed class = Java 的“类型乐高”！](https://mp.weixin.qq.com/s/EWgtX0HEeX3nmsjjC5lP3A)
- **06** `06-pattern-matching-instanceof`：[一行 instanceof 干掉“先判后转”！JDK 16+ 模式匹配让类型检查优雅到飞起](https://mp.weixin.qq.com/s/xRbC0bKSKc_3aHhKGSgtZg)
- **07** `07-var-type-inference`：[var 不是偷懒，是“高级懒”！JDK 10+ 局部变量类型推断让代码更清晰、更安全](https://mp.weixin.qq.com/s/pfyQNYtgTg-qIqhO2u0FJQ)
- **08** `08-string-new-methods`：[别再手写 trim().isEmpty()！JDK 11 给 String 加了三个“神技”](https://mp.weixin.qq.com/s/Zxfr7icjU-9j77VUzw9Wqw)
- **09** `09-method-handle-demo`：[反射太重？试试 MethodHandle！JDK 7 就有的“轻量级反射”，性能超快](https://mp.weixin.qq.com/s/AE00DEBqq94IHXglw6A33Q)
- **10** `10-virtual-threads-demo`：[虚拟线程（Virtual Threads）初体验：10万并发如喝水（JDK 21）](https://mp.weixin.qq.com/s/-WoO4n_v75SCQgOp5_h0AA)
- **11** `11-collector-of-demo`：[Stream.collect() 的花式玩法：Collector.of() 自定义收集器](https://mp.weixin.qq.com/s/Y4gNJUfx4V8GdtITwFjwuw)
- **12** `12-structured-concurrency-demo`：[结构化并发：用 StructuredTaskScope 编写结构化的并发代码](https://mp.weixin.qq.com/s/6JPXK5D6jeKm4zMCihiLkg)
- **13** `13-completable-future-chain`：[CompletableFuture 的链式异步：告别回调地狱](https://mp.weixin.qq.com/s/iw27zHyOCuBDDlzCIQdLtw)
- **14** `14-inheritable-threadlocal-context`：[一行代码让异步任务继承主线程的用户信息！InheritableThreadLocal 的正确打开方式](https://mp.weixin.qq.com/s/qkZnN-ItEQ8taE_GBaPuKw)
- **15** `15-mdc-traceability`：[日志打得好，Bug 跑不了！用 MDC 实现全链路追踪](https://mp.weixin.qq.com/s/VhojIYdr6I4TSON_E4l_tA)
- **16** `16-objects-equals-hashcode`：[用 Objects 工具类告别手写 equals 和 hashCode](https://mp.weixin.qq.com/s/9mQNn6krrTinI78NysXcHA)
- **17** `17-concurrent-hashmap-compute`：[ConcurrentHashMap.computeIfAbsent()：高并发下安全初始化的终极方案](https://mp.weixin.qq.com/s/DziQFcxHvuJQR77oQLyRwg)
- **18** `18-enum-advanced`：[枚举的高级用法——用枚举实现策略模式和状态机](https://mp.weixin.qq.com/s/2kjL71ANpKAWsbDeifDoCg)
- **19** `19-java-time-best-practices`：[Java 日期时间 API（java.time）最佳实践](https://mp.weixin.qq.com/s/VX5qxr93iAbBVujF874g2Q)
- **20** `20-lambda-effectively-final`：[Lambda 表达式中的变量捕获与 effectively final](https://mp.weixin.qq.com/s/R_taqoP97gBtEziAXL8qvg)

## 贡献

- 欢迎提交 PR：补充示例、增加测试、完善文档、修复错误。
- 建议保持每个主题“自解释 + 可运行”的风格：有入口类、有最小可复现代码、有输出或断言。

## License

本项目采用 [MIT License](./LICENSE)。