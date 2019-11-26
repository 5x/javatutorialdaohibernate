package dao.datasource;

import java.sql.Connection;

public interface BasicDataSource {
    Connection getConnection();
    void closeConnection();
}
