package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.model.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieFullMapper implements RowMapper<Movie> {
    private MovieMapper mapper;

    public MovieFullMapper(MovieMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie result = mapper.mapRow(resultSet, i);
        result.setDescription(resultSet.getString("description"));

        return result;
    }
}
