package brewery.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractDAO {

    private final SessionFactory sessionFactory;

    public AbstractDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session openSession() {
        return this.sessionFactory.openSession();
    }

}
