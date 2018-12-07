package db2.onlineshop.service.fx.impl;

import db2.onlineshop.service.fx.CurrencyDao;
import db2.onlineshop.service.fx.entity.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DefaultCurrencyDao implements CurrencyDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private volatile Map<String, Double> cache;

    private String url;
    private String pattern;

    private RestTemplate restTemplate;

    @Override
    public double get(String code) {
        double result = cache.get(code);
        log.trace("get:result={}", result);

        return result;
    }

    @PostConstruct
    @Scheduled(fixedDelayString = "${currency.schedule.fixedDelay}", initialDelayString = "${currency.schedule.initialDelay}")
    public void refreshCache() {
        Map<String, Double> result = getAll();
        log.debug("refreshCache:result.size={}", result.size());

        cache = result;
    }

    public Map<String, Double> getAll() {
        long startTime = System.currentTimeMillis();
        String uri = getUri();
        String dateParam = getDateParam();
        Currency[] response = restTemplate.getForObject(uri, Currency[].class, dateParam);
        log.debug("getAll:response={}", response);

        Map<String, Double> result = new HashMap<>();
        for (Currency currency : response) {
            String code = currency.getCode();
            result.put(code, currency.getRate());
        }

        log.debug("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    private String getUri() {
        String result = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("json")
                .toUriString();
        log.debug("getUri:result={}", result);

        return result;
    }

    private String getDateParam() {
        String result = LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
        log.debug("getDateParam:result={}", result);

        return result;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${currency.url.default}")
    public void setUrl(String url) {
        this.url = url;
    }

    @Value("${currency.pattern}")
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
