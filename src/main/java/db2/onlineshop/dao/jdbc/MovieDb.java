package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.Aggregate;
import db2.onlineshop.dao.jdbc.mapper.MovieMapper;
import db2.onlineshop.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDb extends TemplateDb<Movie, Integer> implements Aggregate<Integer> {
    private static final MovieMapper ROW_MAPPER = new MovieMapper();

    @Autowired
    String sqlSelectMovies;
    @Autowired
    String sqlFetchMovie;
    @Autowired
    String dmlUpdateMovie;
    @Autowired
    String dmlInsertMovie;
    @Autowired
    String dmlDeleteMovie;
    // aggregate
    @Autowired
    String sqlMaxId;

    @Override
    Class getEntityClass() { return Movie.class; }
    @Override
    Class getKeyClass() { return Integer.class; }

    @Override
    RowMapper getRowMapper() { return ROW_MAPPER; }

    @Override
    String getSqlSelectAll() { return sqlSelectMovies; }
    @Override
    String getSqlFetchRow() { return sqlFetchMovie; }
    @Override
    String getDmlUpdateRow() { return dmlUpdateMovie; }
    @Override
    String getDmlInsertRow() { return dmlInsertMovie; }
    @Override
    String getDmlDeleteRow() { return dmlDeleteMovie; }

    @Override
    final MapSqlParameterSource prepareUpdate(Movie version) {
        MapSqlParameterSource result = new MapSqlParameterSource();
        result.addValue("id", version.getId());
        result.addValue("name", version.getName());

        return result;
    }

    @Override
    MapSqlParameterSource prepareInsert(Movie version) {
        MapSqlParameterSource result = new MapSqlParameterSource();
        result.addValue("name", version.getName());
        result.addValue("price", version.getPrice());

        return result;
    }

    @Override
    public Integer getMaxKey() {
        int result = fetchKey(sqlMaxId);
        return result;
    }
}
