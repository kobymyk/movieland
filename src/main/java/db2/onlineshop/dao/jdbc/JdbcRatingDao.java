package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.RatingDao;
import db2.onlineshop.dao.jdbc.mapper.RatingMapper;
import db2.onlineshop.entity.Rating;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcRatingDao implements RatingDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final RatingMapper ROW_MAPPER = new RatingMapper();

    private BasicDataSource dataSource;
    private SimpleJdbcCall jdbcCall;

    @Override
    public void add(Rating rating) {
        int movieId = rating.getMovieId();
        log.trace("add:movieId={}", movieId);

        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("p_movie_id", movieId)
            .addValue("p_user_id", rating.getUser().getId())
            .addValue("p_rating", rating.getRating());

        jdbcCall.withCatalogName("pkg_movie")
                .withProcedureName("insert_movie_rating")
                .execute(params);
    }

    @Autowired
    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;

        this.jdbcCall = new SimpleJdbcCall(dataSource);
    }

}
