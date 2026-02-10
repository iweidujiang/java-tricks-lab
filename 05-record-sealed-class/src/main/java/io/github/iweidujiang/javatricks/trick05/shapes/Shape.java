package io.github.iweidujiang.javatricks.trick05.shapes;

/**
 * 定义形状接口
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/10 15:25
 */
public sealed interface Shape permits Circle, Rectangle, Triangle {}
