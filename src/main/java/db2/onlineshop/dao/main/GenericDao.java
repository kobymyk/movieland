package db2.onlineshop.dao.main;

import java.util.List;

//todo: GenericDao<T, I>
public interface GenericDao<T> {
    void add(T entity);
    T edit(T entity);
    List<T> getAll();
    T getById(int id);
    List<T> listByKey(String key, Object value);
    T getByKey(String key, Object value);
}
