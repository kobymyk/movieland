package db2.onlineshop.dao.main;

import db2.onlineshop.entity.main.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("select m from Movie m join MovieGenre x where x.genre.id = :genreId")
    List<Movie> findByGenreId(@Param("genreId") Integer genreId);
}
