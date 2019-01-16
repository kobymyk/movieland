package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.compound.GenreItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<GenreItem> {
    @Override
    public GenreItem mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        GenreItem result = new GenreItem(id, name);

        return result;
    }
}
