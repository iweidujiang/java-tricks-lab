package io.github.iweidujiang.javatricks.trick14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 模拟支付服务
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/8
 */
public class PaymentService {
    public void deductBalance(Connection conn, String userId, int amount) throws SQLException {
        String sql = "UPDATE t_users SET balance = balance - ? WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, amount);
            stmt.setString(2, userId);
            int updated = stmt.executeUpdate();
            if (updated == 0) {
                throw new SQLException("用户不存在或余额不足");
            }
            System.out.println("【支付】扣款成功: " + amount);
        }
    }
}
