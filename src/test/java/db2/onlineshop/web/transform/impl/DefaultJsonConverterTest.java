package db2.onlineshop.web.transform.impl;

import db2.onlineshop.entity.Movie;
import org.junit.Test;


import static org.junit.Assert.assertEquals;


public class DefaultJsonConverterTest {
    @Test
    public void testMapRowWithProperCity() {
        Movie movie = new Movie(1, "name1", "nameNative1", 1000, 11, 111, "picturePath");


        String actual = new DefaultJsonConverter().toJson(movie);
        String expected = "{\n  \"id\" : 1,\n  \"name\" : \"name1\",\n  \"nameNative\" : \"nameNative1\",\n  " +
            "\"yearOfRelease\" : 1000,\n  \"rating\" : 11.0,\n  \"price\" : 111.0\n}";

        assertEquals(expected, actual);
    }
}
