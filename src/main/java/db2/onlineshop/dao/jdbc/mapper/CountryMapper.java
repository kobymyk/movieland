package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.compound.CountryItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements RowMapper<CountryItem> {
    @Override
    public CountryItem mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        CountryItem result = new CountryItem(id, name);

        return result;
    }
}
