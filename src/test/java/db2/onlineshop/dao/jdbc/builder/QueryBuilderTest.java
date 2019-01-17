package db2.onlineshop.dao.jdbc.builder;

import db2.onlineshop.entity.SortOrder;
import db2.onlineshop.entity.Ordering;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class QueryBuilderTest {
    final String original = "SELECT * FROM dual";

    @Test
    public void wrap() {
        String expected = "SELECT * FROM ( SELECT * FROM dual )";

        String actual = new QueryBuilder(original).wrap().build();

        assertEquals(expected, actual);
    }

    @Test
    public void sort() {
        String expected = "SELECT * FROM dual ORDER BY dummy ASC";

        Ordering param = new Ordering("dummy", SortOrder.ASC);
        String actual = new QueryBuilder(original).addSort(param).build();

        assertEquals(expected, actual);
    }
}