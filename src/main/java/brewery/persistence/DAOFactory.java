package brewery.persistence;

import brewery.persistence.dao.*;
import dao.DAOException;

/**
 * Abstract class DAO Factory
 */
public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(AvailableImplementations whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return new MySQLDAOFactory();
            case HIBERNATE:
                return new HibernateDAOFactory();
            default:
                throw new DAOException("DAO Provider don`t supported.");
        }
    }

    public abstract void closeSession();

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract IBatch getBatchDAO();

    public abstract IBeerStyle getBeerStyleDAO();

    public abstract IBusinessFactory getBusinessFactoryDAO();

    public abstract ICity getCityDAO();

    public abstract IFactoryUnit getFactoryUnitDAO();

    public abstract ITrader getTraderDAO();

    public abstract IWarehouse getWarehouseDAO();

    public enum AvailableImplementations {
        MYSQL,
        HIBERNATE
    }

}
