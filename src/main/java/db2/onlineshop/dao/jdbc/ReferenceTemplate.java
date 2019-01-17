package db2.onlineshop.dao.jdbc;

import java.util.List;

public abstract class ReferenceTemplate<T, E> extends EntityTemplate<T> {
    private Class<E> parentClass;

    protected final void setParentClass(final Class<E> parentClass) {
        log.trace("setDictionaryClass:dictionaryClass={}", parentClass);
        this.parentClass = parentClass;
    }

    public final List<T> getByMovie1(int movieId) {

        return null;
    }
}
