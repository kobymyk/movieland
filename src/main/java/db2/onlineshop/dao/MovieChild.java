package db2.onlineshop.dao;

import java.util.List;

public interface MovieChild<O> {

    List<O> getByMovie(int movieId);
}
