package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.Persistent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Locale;

@Service
public abstract class TemplateDb<T, K> implements Persistent<T, K> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    abstract Class<T> getEntityClass();
    abstract Class<K> getKeyClass();
    abstract RowMapper getRowMapper();

    abstract String getSqlSelectAll();
    abstract String getSqlFetchRow();
    abstract String getDmlUpdateRow();
    abstract String getDmlInsertRow();
    abstract String getDmlDeleteRow();

    public TemplateDb() {
        // XE limitation
        Locale.setDefault(Locale.ENGLISH);
    }

    abstract MapSqlParameterSource prepareUpdate(T version);
    abstract MapSqlParameterSource prepareInsert(T version);

    @Override
    public final T fetchRow(K key) {
        log.info("fetchRow:key={}", key);
        long startTime = System.currentTimeMillis();
        T result = (T) jdbcTemplate.queryForObject(getSqlFetchRow(), new Object[]{key}, getRowMapper());
        log.info("fetchRow:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Override
    public List<T> selectAll() {
        long startTime = System.currentTimeMillis();
        List<T> result  = jdbcTemplate.query(getSqlSelectAll(), new BeanPropertyRowMapper(getEntityClass()));
        log.info("selectAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Override
    public int updateRow(T version) {
        int result = namedJdbcTemplate.update(getDmlUpdateRow(), prepareUpdate(version));
        log.info("updateRow::end");

        return result;
    }

    @Override
    public int insertRow(T version) {
        int result = namedJdbcTemplate.update(getDmlInsertRow(), prepareInsert(version));
        log.info("insertRow::end");

        return result;
    }

    @Override
    public int deleteRow(K key) {
        log.info("deleteRow:key={}", key);
        int result = jdbcTemplate.update(getDmlDeleteRow(), key);
        log.info("deleteRow:result={}", result);

        return result;
    }

    public final K fetchKey(String sql) {
        log.info("fetchRow/start");
        long startTime = System.currentTimeMillis();
        K result = jdbcTemplate.queryForObject(sql, getKeyClass());
        log.info("fetchRow:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }
}
