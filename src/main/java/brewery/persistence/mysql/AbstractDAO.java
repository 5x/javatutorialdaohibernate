package brewery.persistence.mysql;

import java.sql.Connection;

public abstract class AbstractDAO {

    private final Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    protected Connection getConnection() {
        return this.connection;
    }
}
