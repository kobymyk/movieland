package db2.onlineshop.dao;

import java.util.List;

@Deprecated
public interface Persistent<T, K> {
    List<T> selectAll();

    T fetchRow(K key);

    int updateRow(T version);

    int insertRow(T version);

    int deleteRow(K key);


}
