package ru.sbercourse.home_work_6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public final class DBConnection {
    private Connection connection;
    private final Logger LOGGER = LoggerFactory.getLogger(DBConnection.class);


    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres", "1");
            }
        } catch (SQLException e) {
            LOGGER.error("Connection to the database failed", e);
            throw new RuntimeException(e);
        }
        return connection;
    }
}
