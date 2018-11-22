package db2.onlineshop.dao;

public interface Aggregate<K> {
    K getMaxKey();
}
