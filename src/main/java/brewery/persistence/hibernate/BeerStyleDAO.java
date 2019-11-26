package brewery.persistence.hibernate;

import brewery.persistence.dao.IBeerStyle;
import brewery.persistence.entities.BeerStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateQueryUtils;

import java.util.List;

public class BeerStyleDAO extends AbstractDAO implements IBeerStyle {
    public BeerStyleDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Integer> findAll() {
        Session session = this.openSession();
        return HibernateQueryUtils.findAll(session, BeerStyle.class);
    }

    @Override
    public Integer read(BeerStyle id) {
        Session session = this.openSession();
        return HibernateQueryUtils.read(session, BeerStyle.class, id);
    }

    @Override
    public void update(Integer entity) {
        Session session = this.openSession();
        HibernateQueryUtils.update(session, entity);
    }

    @Override
    public void delete(BeerStyle id) {
        Session session = this.openSession();
        HibernateQueryUtils.delete(session, BeerStyle.class, id);
    }

    @Override
    public BeerStyle create(Integer entity) {
        Session session = this.openSession();
        return HibernateQueryUtils.create(session, entity);
    }
}
