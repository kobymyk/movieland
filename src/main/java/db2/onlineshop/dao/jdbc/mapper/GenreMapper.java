package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class GenreMapper implements RowMapper<Genre> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Genre mapRow(ResultSet resultSet, int i) {
        Genre result = new Genre();
        try {
            result.setId(resultSet.getInt("id"));
            result.setName(resultSet.getString("name"));

            return result;
        } catch (Exception e) {
            log.error("mapRow:Exception={}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
