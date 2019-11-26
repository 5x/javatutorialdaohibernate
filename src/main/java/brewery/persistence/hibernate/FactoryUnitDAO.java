package brewery.persistence.hibernate;

import brewery.persistence.dao.IFactoryUnit;
import brewery.persistence.entities.BeerStyle;
import brewery.persistence.entities.FactoryUnit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateQueryUtils;

import java.util.List;

public class FactoryUnitDAO extends AbstractDAO implements IFactoryUnit {
    public FactoryUnitDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Integer getBeerSortProductionVolume(FactoryUnit factoryUnit, BeerStyle beerStyle) {
        return null;
    }

    @Override
    public List<Integer> findAll() {
        Session session = this.openSession();
        return HibernateQueryUtils.findAll(session, FactoryUnit.class);
    }

    @Override
    public Integer read(FactoryUnit id) {
        Session session = this.openSession();
        return HibernateQueryUtils.read(session, FactoryUnit.class, id);
    }

    @Override
    public void update(Integer entity) {
        Session session = this.openSession();
        HibernateQueryUtils.update(session, entity);
    }

    @Override
    public void delete(FactoryUnit id) {
        Session session = this.openSession();
        HibernateQueryUtils.delete(session, FactoryUnit.class, id);
    }

    @Override
    public FactoryUnit create(Integer entity) {
        Session session = this.openSession();
        return HibernateQueryUtils.create(session, entity);
    }
}
