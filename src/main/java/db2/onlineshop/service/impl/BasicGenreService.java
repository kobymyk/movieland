package db2.onlineshop.service.impl;

import db2.onlineshop.dao.jdbc.GenreDb;
import db2.onlineshop.entity.Genre;
import db2.onlineshop.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicGenreService implements GenreService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private GenreDb genreDb;

    @Override
    public List<Genre> getAll() {
        return genreDb.getAll();
    }

    @Autowired
    public void setGenreDb(GenreDb genreDb) {
        this.genreDb = genreDb;
    }
}
