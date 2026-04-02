# java-tricks-lab

深耕 Java 技术栈的「技巧实验室」：把实战开发里那些**更高效的写法、更稳的工程姿势、更能打的性能与并发方案**，拆成一个个可复现的最小示例与文章。

从基础语法优化到高级特性/框架应用——**每篇文章聚焦一个具体问题**，给出清晰结论与可落地的解决方案，我们一起写出更优雅、更健壮的 Java 代码。

## 你能在这里收获什么

- **更快**：常见代码写法的“提速按钮”（少写、更清晰、更不容易踩坑）
- **更稳**：异常/资源/并发等高频问题的工程级最佳实践
- **更强**：性能调优、代码重构、设计模式等核心主题的可运行示例
- **更体系**：每个主题都有对应目录与示例代码，方便按需查阅与复盘

## 内容版图（持续更新）

- **语言特性与语法糖**：写得更简洁，但不牺牲可读性与可维护性
- **并发与异步**：从线程模型到结构化并发、CompletableFuture 链式编排等
- **性能与诊断**：常见性能陷阱、可度量的优化方式、必要时引入基准测试
- **工程实践**：资源管理、日志链路、等价性/哈希、时间 API 等“基础但关键”的正确姿势
- **设计模式与重构**：用更少的复杂度，换更高的扩展性与可测试性

## 快速开始

- 多数示例目录是独立 Maven 工程（目录下有 `pom.xml`），直接用 IDE 运行 `Main` 即可。
- 建议使用 **JDK 21**；若版本不匹配，以对应模块 `pom.xml` 的 `maven.compiler.source/target` 为准。

## 适合谁

- **想把 Java 写得更漂亮的人**：追求表达力、边界清晰、少踩坑
- **在业务与性能之间拉扯的人**：需要可解释、可度量、可复现的优化路径
- **不想“只会背八股”的人**：希望把知识变成“能落地的手感”


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