package dao.datasource;

import dao.DAOConnectionException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDataSource implements BasicDataSource {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/brewery?useSSL=false";
    private static final String DB_USERNAME = "java_db_user";
    private static final String DB_PASSWORD = "{{DB_PASSWORD}}";
    private static final String JDBC_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    private Connection connection;

    private void registerDriver(String driverClassName) {
        try {
            Driver driver = (Driver) Class.forName(driverClassName).newInstance();
            DriverManager.registerDriver(driver);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            String message = String.format("Cannot find JDBC driver(\"%s\").", driverClassName);
            throw new DAOConnectionException(message, e);
        }
    }

    private Connection createConnection() {
        try {
            this.registerDriver(JDBC_DRIVER_CLASS_NAME);
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            String message = String.format(
                    "Cannot connect with the database. JDBC Connection URL: %s.", DB_URL);
            throw new DAOConnectionException(message, e);
        }
    }

    @Override
    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                this.connection = this.createConnection();
            }
        } catch (SQLException e) {
            throw new DAOConnectionException("Cannot create DB connection.", e);
        }

        return this.connection;
    }

    @Override
    public void closeConnection() {
        try {
            if (!this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            throw new DAOConnectionException("A database access error occurs", e);
        }
    }
}
