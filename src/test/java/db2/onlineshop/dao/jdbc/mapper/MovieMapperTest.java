package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Movie;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieMapperTest {
    @Test
    public void testMapRow() throws SQLException {
        MovieMapper mapper = new MovieMapper();
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt(any())).thenReturn(1).thenReturn(1000);
        when(resultSet.getString(any())).thenReturn("name").thenReturn("name_native").thenReturn("picture_path");
        when(resultSet.getDouble("rating")).thenReturn(10.1);
        when(resultSet.getDouble("price")).thenReturn(100.01);

        Movie actual = mapper.mapRow(resultSet, 0);
        assertEquals(actual.getId(), 1);
        assertEquals(actual.getYearOfRelease(), 1000);
        assertEquals(actual.getName(), "name");
        assertEquals(actual.getNameNative(), "name_native");
        assertNull(actual.getDescription());
        assertEquals(actual.getPicturePath(), "picture_path");
        assertEquals(actual.getRating(), 10.1, 1);
        assertEquals(actual.getPrice(), 100.01, 2);
    }
}
