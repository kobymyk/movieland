package db2.onlineshop.dao.impl;

import db2.onlineshop.dao.main.GenericDao;
import db2.onlineshop.entity.main.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class CacheGenreDao extends GenreDaoImpl {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final GenericDao<Genre> genreDao;
    private volatile List<Genre> cache;

    public CacheGenreDao(GenericDao<Genre> genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAll() {
        log.trace("getAll:cache.size={}", cache.size());
        // return copy
        return new ArrayList<>(cache);
    }

    @PostConstruct
    @Scheduled(fixedDelayString = "${genre.schedule.fixedDelay}", initialDelayString = "${genre.schedule.initialDelay}")
    public void refreshCache() {
        long startTime = System.currentTimeMillis();
        cache = genreDao.getAll();
        log.debug("refreshCache:duration={}", System.currentTimeMillis() - startTime);
    }
}
