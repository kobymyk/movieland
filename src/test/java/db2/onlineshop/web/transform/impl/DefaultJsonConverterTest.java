package db2.onlineshop.web.transform.impl;

import db2.onlineshop.entity.Movie;
import org.junit.Test;


import static org.junit.Assert.assertEquals;


public class DefaultJsonConverterTest {
    @Test
    public void testMapRowWithProperCity() {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setName("name1");

        String actual = new DefaultJsonConverter().toJson(movie);
        String expected = "[ { \"id\" : 1, \"name\" : \"name1\" }";

        assertEquals(expected, actual);
    }
}
