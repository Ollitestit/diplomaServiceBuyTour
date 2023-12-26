package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static String getDebitCardPaymentStatus() {
        var statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConnection();
        return runner.query(conn, statusSQL, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static String getCreditPaymentStatus() {
        var statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConnection();
        return runner.query(conn, statusSQL, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var conn = getConnection();
        runner.execute(conn, "DELETE FROM credit_request_entity");
        runner.execute(conn, "DELETE FROM order_entity");
        runner.execute(conn, "DELETE FROM payment_entity");
    }

}
