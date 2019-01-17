package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.MovieCompoundDao;
import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.MovieCompound;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.MovieCountry;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JdbcMovieCompoundDao extends EntityTemplate<MovieCompound> implements MovieCompoundDao {

    @Transactional
    @Override
    public void add(MovieCompound entity) {
        Movie movie = entity.getMovie();
        persist(movie);
        int movieId = movie.getId();

        MovieCountry movieCountry;
        for (Country country : entity.getCountries()) {
            movieCountry = new MovieCountry();
            movieCountry.setMovieId(movieId);
            persist(movieCountry);
        }

    }
}
