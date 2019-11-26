package brewery.persistence.hibernate;

import brewery.persistence.dao.IBatch;
import brewery.persistence.entities.Batch;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateQueryUtils;

import java.util.List;

public class BatchDAO extends AbstractDAO implements IBatch {
    public BatchDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Integer> findAll() {
        Session session = this.openSession();
        return HibernateQueryUtils.findAll(session, Batch.class);
    }

    @Override
    public Integer read(Batch id) {
        Session session = this.openSession();
        return HibernateQueryUtils.read(session, Batch.class, id);
    }

    @Override
    public void update(Integer entity) {
        Session session = this.openSession();
        HibernateQueryUtils.update(session, entity);
    }

    @Override
    public void delete(Batch id) {
        Session session = this.openSession();
        HibernateQueryUtils.delete(session, Batch.class, id);
    }

    @Override
    public Batch create(Integer entity) {
        Session session = this.openSession();
        return HibernateQueryUtils.create(session, entity);
    }

}
