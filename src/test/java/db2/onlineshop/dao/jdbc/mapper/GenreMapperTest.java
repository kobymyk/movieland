package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Genre;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenreMapperTest {
    @Test
    public void testMapRow() throws SQLException {
        GenreMapper mapper = new GenreMapper();
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("name 1");

        Genre actual = mapper.mapRow(resultSet, 0);
        assertEquals(actual.getId(), 1);
        assertEquals(actual.getName(), "name 1");
    }
}
