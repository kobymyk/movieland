package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.Persistent;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PersistentTemplate<T> implements Persistent<T> {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private Class<T> modelType;

    private SessionFactory sessionFactory;

    @Override
    public final void add(T entity) {
        Session session = getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
    }

    protected final void setModelType(final Class<T> modelType) {
        this.modelType = modelType;
    }

    protected final Session getSession() {
        Session result;
        try {
            result = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            result = sessionFactory.openSession();
        }
        return result;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
