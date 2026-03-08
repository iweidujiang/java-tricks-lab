package io.github.iweidujiang.javatricks.trick14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 模拟订单服务
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/8
 */
public class OrderService {
    public void insertOrder(Connection conn, String orderId, String userId) throws SQLException {
        String sql = "INSERT INTO t_orders (order_id, user_id, status) VALUES (?, ?, 'CREATED')";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, orderId);
            stmt.setString(2, userId);
            stmt.executeUpdate();
            System.out.println("【订单】创建订单: " + orderId);
        }
    }
}
