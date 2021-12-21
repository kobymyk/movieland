package db2.onlineshop.service.impl;

import db2.onlineshop.dao.generic.GenericDao;
import db2.onlineshop.entity.main.Genre;
import db2.onlineshop.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final GenericDao<Genre> genreDao;

    @Autowired
    public GenreServiceImpl(GenericDao<Genre> genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> result = genreDao.getAll();
        log.info("getAll:result.size={}", result.size());

        return result;
    }

}
