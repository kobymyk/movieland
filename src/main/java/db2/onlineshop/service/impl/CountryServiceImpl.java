package db2.onlineshop.service.impl;

import db2.onlineshop.dao.common.CountryRepository;
import db2.onlineshop.entity.common.Country;
import db2.onlineshop.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getAll() {
        log.debug("getAll");
        List<Country> result = countryRepository.findAll();
        log.debug("getAll:result.size={}", result.size());

        return result;
    }

}
