package db2.onlineshop.dao.main;

import db2.onlineshop.entity.main.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    //List<Movie> findByGenreId(Integer genreId);
}
