package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Country;
import db2.onlineshop.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/country")
public class CountryController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private CountryService countryService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Country> getAll() {
        log.info("getAll");
        long startTime = System.currentTimeMillis();
        List<Country> result = countryService.getAll();
        log.info("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Autowired
    public void setGenreService(CountryService countryService) {
        this.countryService = countryService;
    }
}
