package dao.datasource;

import dao.DAOConnectionException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;


public class HibernateDataSource implements BasicDataSource {
    private Connection connection;
    private SessionFactory factory;

    private void createFactory() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    private Connection createConnection() throws SQLException {
        createFactory();
        return factory.getSessionFactoryOptions().getServiceRegistry().
                getService(ConnectionProvider.class).getConnection();
    }

    public SessionFactory getSessionFactory() {
        return this.factory;
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
