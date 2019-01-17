package db2.onlineshop.dao;

public interface Persistent<T> {

    void add(T entity);
}
