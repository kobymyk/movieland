package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieFullMapper extends MovieMapper {
    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie result = super.mapRow(resultSet, i);
        result.setDescription(resultSet.getString("description"));

        return result;
    }
}
