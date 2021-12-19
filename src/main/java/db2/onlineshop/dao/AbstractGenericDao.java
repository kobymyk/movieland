package db2.onlineshop.dao;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class AbstractGenericDao<T> implements GenericDao<T> {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private Class<T> entityClass;

    @Autowired
    private SessionFactory sessionFactory;

    protected void persist(Object entity) {
        log.trace("save:entity={}", entity);
        getSession().persist(entity);
    }

    @Override
    public void add(T entity) {
        persist(entity);
    }

    @Override
    public final T edit(T entity) {
        log.trace("edit:entity={}", entity);
        T result = (T) getSession().merge(entity);
        log.trace("edit:result={}", result);

        return result;
    }

    @Override
    public List<T> getAll() {
        Criteria criteria = getCriteria();
        addOrdering(criteria);

        List<T> result = criteria.list();
        log.trace("getAll:result={}", result);

        return result;
    }

    @Override
    public T getById(int id) {
        T result = getSession().get(entityClass, id);
        log.debug("getById:result={}", result);

        return result;
    }

    @Override
    public List<T> listByKey(String key, Object value) {
        log.trace("listByKey:key={};value={}", key, value);
        //todo: query
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq(key, value));
        List<T> result = criteria.list();
        log.trace("listByKey:result={}", result);

        return result;
    }

    @Override
    public T getByKey(String key, Object value) {
        log.trace("getByKey:key={};value={}", key, value);
        //todo: query
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq(key, value));
        T result = (T) criteria.uniqueResult();
        log.trace("getByKey:result={}", result);

        return result;
    }

    protected void addOrdering(Criteria criteria) {

    }

    protected final void setEntityClass(final Class<T> entityClass) {
        log.trace("setEntityClass:entityClass={}", entityClass);
        this.entityClass = entityClass;
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

    protected final Criteria getCriteria() {
        return getSession().createCriteria(entityClass);
    }

}
