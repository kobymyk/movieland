package db2.onlineshop.dao;

import java.util.List;
import java.util.Optional;

public interface PersistOperation<T> {

    void add(T entity);

    T edit(T entity);

    List<T> getAll();

    T getById(int id);

    List<T> listByKey(String key, Object value);

    Optional<T> getByKey(String key, Object value);
}
