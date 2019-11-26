package utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HibernateQueryUtils {
    public static <E extends Serializable, PK extends Serializable> E read(Session session, Class cls, PK id) {
        Objects.requireNonNull(id, "Invalid id-key value. Must be not null.");

        Transaction transaction = session.getTransaction();
        E entity;

        try {
            transaction.begin();
            entity = (E) session.get(cls, id);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return entity;
    }

    public static <E extends Serializable, PK extends Serializable> PK create(Session session, E entity) {
        Objects.requireNonNull(entity);

        Transaction transaction = session.getTransaction();
        PK entityId;

        try {
            transaction.begin();
            entityId = (PK) session.save(entity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return entityId;
    }


    public static <E extends Serializable, PK extends Serializable> void delete(Session session, Class cls, PK id) {
        Objects.requireNonNull(id, "Invalid id-key value. Must be not null.");

        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();

            E entity = (E) session.get(cls, id);
            if (!Objects.isNull(entity)) {
                session.delete(entity);
            }

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static <E extends Serializable> void update(Session session, E entity) {
        Objects.requireNonNull(entity);

        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            session.update(entity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static <E extends Serializable> List<E> findAll(Session session, Class cls) {
        Transaction transaction = session.getTransaction();
        List<E> entities;

        try {
            transaction.begin();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<E> criteria = (CriteriaQuery<E>) builder.createQuery(cls);
            Root<E> root = (Root<E>) criteria.from(cls);
            criteria.select(root);

            Query<E> query = session.createQuery(criteria);
            entities = query.getResultList();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        if (Objects.isNull(entities)) {
            return new ArrayList<>();
        }

        return entities;
    }


}

