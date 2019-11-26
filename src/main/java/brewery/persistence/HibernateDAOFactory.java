package brewery.persistence;

import brewery.persistence.dao.*;
import brewery.persistence.hibernate.*;
import dao.datasource.HibernateDataSource;
import org.hibernate.SessionFactory;


public class HibernateDAOFactory extends DAOFactory {
    private final SessionFactory sessionFactory;
    private final HibernateDataSource dataSource;

    HibernateDAOFactory() {
        this.dataSource = new HibernateDataSource();
        this.dataSource.getConnection();
        this.sessionFactory = this.dataSource.getSessionFactory();
    }

    @Override
    public void closeSession() {
        this.dataSource.closeConnection();
    }

    @Override
    public IBatch getBatchDAO() {
        return new BatchDAO(this.sessionFactory);
    }

    @Override
    public IBeerStyle getBeerStyleDAO() {
        return new BeerStyleDAO(this.sessionFactory);
    }

    @Override
    public IBusinessFactory getBusinessFactoryDAO() {
        return new BusinessFactoryDAO(this.sessionFactory);
    }

    @Override
    public ICity getCityDAO() {
        return new CityDAO(this.sessionFactory);
    }

    @Override
    public IFactoryUnit getFactoryUnitDAO() {
        return new FactoryUnitDAO(this.sessionFactory);
    }

    @Override
    public ITrader getTraderDAO() {
        return new TraderDAO(this.sessionFactory);
    }

    @Override
    public IWarehouse getWarehouseDAO() {
        return new WarehouseDAO(this.sessionFactory);
    }
}
