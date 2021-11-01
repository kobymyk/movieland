package db2.onlineshop.web.controller;

import db2.onlineshop.entity.common.Country;
import db2.onlineshop.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CountryController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ResponseBody
    @GetMapping(value = "/v1/country", produces = {MediaType.APPLICATION_JSON_VALUE})
    //@PreAuthorize("hasAuthority('user:read')")
    public List<Country> getAll() {
        log.info("getAll");
        long startTime = System.currentTimeMillis();
        List<Country> result = countryService.getAll();
        log.info("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @GetMapping(value = "/country")
    //@PreAuthorize("hasAuthority('user:read')")
    public String list(Model model) {
        List<Country> countries = countryService.getAll();
        model.addAttribute("countries", countries);

        return "countryList.html";
    }

}
