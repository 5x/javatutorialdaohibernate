package brewery.persistence.hibernate;

import brewery.persistence.dao.ITrader;
import brewery.persistence.entities.Trader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateQueryUtils;

import java.util.List;


public class TraderDAO extends AbstractDAO implements ITrader {
    public TraderDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Integer> findAll() {
        Session session = this.openSession();
        return HibernateQueryUtils.findAll(session, Trader.class);
    }

    @Override
    public Integer read(Trader id) {
        Session session = this.openSession();
        return HibernateQueryUtils.read(session, Trader.class, id);
    }

    @Override
    public void update(Integer entity) {
        Session session = this.openSession();
        HibernateQueryUtils.update(session, entity);
    }

    @Override
    public void delete(Trader id) {
        Session session = this.openSession();
        HibernateQueryUtils.delete(session, Trader.class, id);
    }

    @Override
    public Trader create(Integer entity) {
        Session session = this.openSession();
        return HibernateQueryUtils.create(session, entity);
    }
}
