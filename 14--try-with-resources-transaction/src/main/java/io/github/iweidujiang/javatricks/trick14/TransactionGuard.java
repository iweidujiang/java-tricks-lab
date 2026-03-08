package io.github.iweidujiang.javatricks.trick14;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务守卫：利用 try-with-resources 自动提交/回滚
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/8
 */
public class TransactionGuard implements AutoCloseable {
    private final Connection connection;
    private boolean commitOnClose = true;

    public TransactionGuard(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(false);
        System.out.println("【事务】开启事务");
    }

    /**
     * 标记需要回滚（通常在 catch 块中调用）
     */
    public void markForRollback() {
        this.commitOnClose = false;
    }

    @Override
    public void close() throws SQLException {
        SQLException closeException = null;
        try {
            if (commitOnClose) {
                connection.commit();
                System.out.println("【事务】提交成功");
            } else {
                connection.rollback();
                System.out.println("【事务】已回滚");
            }
        } catch (SQLException e) {
            closeException = e;
        } finally {
            try {
                connection.close();
                System.out.println("【事务】连接已关闭");
            } catch (SQLException e) {
                if (closeException == null) {
                    closeException = e;
                } else {
                    closeException.addSuppressed(e);
                }
            }
        }
        if (closeException != null) {
            throw closeException;
        }
    }
}
