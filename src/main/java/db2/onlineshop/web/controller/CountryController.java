package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Country;
import db2.onlineshop.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class CountryController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private CountryService countryService;

    @ResponseBody
    @GetMapping(value = "/v1/country", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Country> getAll() {
        log.info("getAll");
        long startTime = System.currentTimeMillis();
        List<Country> result = countryService.getAll();
        log.info("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @GetMapping(value = "/country")
    public String list(Model model) {
        List<Country> countries = countryService.getAll();
        model.addAttribute("countries", countries);

        return "countryList.html";
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
}
