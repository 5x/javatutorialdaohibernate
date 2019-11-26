package brewery.persistence.hibernate;

import brewery.persistence.dao.IWarehouse;
import brewery.persistence.entities.Warehouse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateQueryUtils;

import java.util.List;


public class WarehouseDAO extends AbstractDAO implements IWarehouse {
    public WarehouseDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Integer> findAll() {
        Session session = this.openSession();
        return HibernateQueryUtils.findAll(session, Warehouse.class);
    }

    @Override
    public Integer read(Warehouse id) {
        Session session = this.openSession();
        return HibernateQueryUtils.read(session, Warehouse.class, id);
    }

    @Override
    public void update(Integer entity) {
        Session session = this.openSession();
        HibernateQueryUtils.update(session, entity);
    }

    @Override
    public void delete(Warehouse id) {
        Session session = this.openSession();
        HibernateQueryUtils.delete(session, Warehouse.class, id);
    }

    @Override
    public Warehouse create(Integer entity) {
        Session session = this.openSession();
        return HibernateQueryUtils.create(session, entity);
    }
}
