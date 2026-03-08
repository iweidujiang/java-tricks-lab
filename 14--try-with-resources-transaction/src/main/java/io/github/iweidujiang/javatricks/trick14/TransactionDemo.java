package io.github.iweidujiang.javatricks.trick14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 演示：用 try-with-resources 自动管理事务
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/8
 */
public class TransactionDemo {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

        // 初始化数据库
        try (Connection initConn = DriverManager.getConnection(jdbcUrl)) {
            try (Statement stmt = initConn.createStatement()) {
                stmt.execute("CREATE TABLE t_users (user_id VARCHAR(50), balance INT)");
                stmt.execute("CREATE TABLE t_orders (order_id VARCHAR(50), user_id VARCHAR(50), status VARCHAR(20))");
                stmt.execute("INSERT INTO t_users VALUES ('USER-123', 100)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();

        try (Connection conn = DriverManager.getConnection(jdbcUrl);
             TransactionGuard guard = new TransactionGuard(conn)) {

            // 执行业务
            orderService.insertOrder(conn, "ORDER-789", "USER-123");
            paymentService.deductBalance(conn, "USER-123", 50);

            // 模拟业务异常（取消注释可测试回滚）
            throw new RuntimeException("模拟业务失败");

        } catch (SQLException | RuntimeException e) {
            System.err.println("【主程序】捕获异常: " + e.getMessage());
            // 注意：TransactionGuard 已自动回滚，无需手动处理
        }
    }
}
