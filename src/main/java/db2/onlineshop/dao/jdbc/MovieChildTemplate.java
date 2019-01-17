package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.MovieChild;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public abstract class MovieChildTemplate<E, T> extends PersistentTemplate<T> implements MovieChild<E> {
    private Class<E> entityType;

    protected final void setEntityType(final Class<E> entityType) {
        this.entityType = entityType;
    }

    @Override
    public final List<E> getByMovie(int movieId) {
        List<E> result = getSession().createCriteria(entityType)
                .add(Restrictions.eq("movieId", movieId))
                .list();
        log.trace("getByMovie:result={}", result);

        return result;
    }
}
