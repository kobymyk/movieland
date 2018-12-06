package db2.onlineshop.service.fx.impl;

import db2.onlineshop.service.fx.CurrencyDao;
import db2.onlineshop.service.fx.entity.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DefaultCurrencyDao implements CurrencyDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private volatile Map<String, Double> cache;

    private String url;

    private RestTemplate restTemplate;

    @Override
    public double get(String code) {
        // todo: @Scheduled
        refreshCache();

        double result = cache.get(code);
        log.trace("get:result={}", result);

        return result;
    }

    public void refreshCache() {
        Map<String, Double> result = getAll();

        cache = result;
    }

    public Map<String, Double> getAll() {
        Currency[] response = restTemplate.getForObject(url, Currency[].class, getDateParam());

        Map<String, Double> result = new HashMap<>();
        for (Currency currency : response) {
            String code = currency.getCode();
            result.put(code, currency.getRate());
        }

        return result;
    }

    private String getDateParam() {
        //LocalDateTime.now(), "yyyyMMdd"
        String result = "20181204";

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
}
