package brewery.persistence.hibernate;

import brewery.persistence.dao.ICity;
import brewery.persistence.entities.BusinessFactory;
import brewery.persistence.entities.City;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateQueryUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CityDAO extends AbstractDAO implements ICity {
    public CityDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<BusinessFactory> getAllBusinessFactories(City city) {
        return null;
    }

    public List<City> filteredSearch(Integer id, String name, String order) {
        Session session = this.openSession();
        Transaction transaction = session.getTransaction();
        List<City> cities;

        try {
            transaction.begin();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<City> criteriaQuery = builder.createQuery(City.class);
            Root<City> root = criteriaQuery.from(City.class);
            criteriaQuery.select(root);

            Predicate filterById = builder.equal(root.get("id"), id);
            Predicate filterByName = builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");

            if (id != null) {
                criteriaQuery.where(filterById, filterByName);
            } else {
                criteriaQuery.where(filterByName);
            }

            if (order.equals("desc")) {
                criteriaQuery.orderBy(builder.desc(root.get("name")));
            } else {
                criteriaQuery.orderBy(builder.asc(root.get("name")));
            }

            Query<City> query = session.createQuery(criteriaQuery);
            cities = query.getResultList();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        if (Objects.isNull(cities)) {
            return new ArrayList<>();
        }

        return cities;
    }

    @Override
    public List<City> findAll() {
        Session session = this.openSession();
        return HibernateQueryUtils.findAll(session, City.class);
    }

    @Override
    public City read(Integer id) {
        Session session = this.openSession();
        return HibernateQueryUtils.read(session, City.class, id);
    }

    @Override
    public void update(City entity) {
        Session session = this.openSession();
        HibernateQueryUtils.update(session, entity);
    }

    @Override
    public void delete(Integer id) {
        Session session = this.openSession();
        HibernateQueryUtils.delete(session, City.class, id);
    }

    @Override
    public Integer create(City entity) {
        Session session = this.openSession();
        return HibernateQueryUtils.create(session, entity);
    }
}
