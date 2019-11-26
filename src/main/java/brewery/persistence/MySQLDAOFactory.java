package brewery.persistence;

import brewery.persistence.dao.*;
import brewery.persistence.mysql.BusinessFactoryDAO;
import brewery.persistence.mysql.CityDAO;
import dao.datasource.BasicDataSource;
import dao.datasource.MySQLDataSource;

import java.sql.Connection;

public class MySQLDAOFactory extends DAOFactory {
    private BasicDataSource dataSource;
    private Connection connection;

    MySQLDAOFactory() {
        this.dataSource = new MySQLDataSource();
        this.connection = dataSource.getConnection();
    }

    @Override
    public void closeSession() {
        this.dataSource.closeConnection();
    }

    @Override
    public IBatch getBatchDAO() {
        return null;
    }

    @Override
    public IBeerStyle getBeerStyleDAO() {
        return null;
    }

    @Override
    public IBusinessFactory getBusinessFactoryDAO() {
        return new BusinessFactoryDAO(this.connection);
    }

    @Override
    public ICity getCityDAO() {
        return new CityDAO(this.connection);
    }

    @Override
    public IFactoryUnit getFactoryUnitDAO() {
        return null;
    }

    @Override
    public ITrader getTraderDAO() {
        return null;
    }

    @Override
    public IWarehouse getWarehouseDAO() {
        return null;
    }

}
