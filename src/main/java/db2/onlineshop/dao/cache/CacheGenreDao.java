package db2.onlineshop.dao.cache;

import db2.onlineshop.dao.GenreDao;
import db2.onlineshop.dao.jdbc.JdbcGenreDao;
import db2.onlineshop.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class CacheGenreDao extends JdbcGenreDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private GenreDao genreDao;
    private volatile List<Genre> cache;

    @Override
    public List<Genre> getAll() {
        log.trace("getAll:cache.size={}", cache.size());
        List<Genre> result = new ArrayList<>(cache);
        // return new copy
        return result;
    }

    @Autowired
    public void setGenreDao(GenreDao genreDao) { this.genreDao = genreDao; }

    @PostConstruct
    @Scheduled(fixedDelayString = "${genre.schedule.fixedDelay}", initialDelayString = "${genre.schedule.initialDelay}")
    public void refreshCache() {
        long startTime = System.currentTimeMillis();
        cache = genreDao.getAll();
        log.debug("refreshCache:duration={}", System.currentTimeMillis() - startTime);
    }
}
