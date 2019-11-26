package brewery.persistence.hibernate;

import brewery.persistence.dao.IBusinessFactory;
import brewery.persistence.entities.BeerStyle;
import brewery.persistence.entities.BusinessFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateQueryUtils;

import java.util.List;

public class BusinessFactoryDAO extends AbstractDAO implements IBusinessFactory {
    public BusinessFactoryDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Integer getBeerSortProductionVolume(BusinessFactory businessFactory, BeerStyle beerStyle) {
        return null;
    }

    @Override
    public List<BusinessFactory> findAll() {
        Session session = this.openSession();
        return HibernateQueryUtils.findAll(session, BusinessFactory.class);
    }

    @Override
    public BusinessFactory read(Integer id) {
        Session session = this.openSession();
        return HibernateQueryUtils.read(session, BusinessFactory.class, id);
    }

    @Override
    public void update(BusinessFactory entity) {
        Session session = this.openSession();
        HibernateQueryUtils.update(session, entity);
    }

    @Override
    public void delete(Integer id) {
        Session session = this.openSession();
        HibernateQueryUtils.delete(session, BusinessFactory.class, id);
    }

    @Override
    public Integer create(BusinessFactory entity) {
        Session session = this.openSession();
        return HibernateQueryUtils.create(session, entity);
    }
}
