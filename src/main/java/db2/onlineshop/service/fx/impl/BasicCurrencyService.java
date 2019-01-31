package db2.onlineshop.service.fx.impl;

import db2.onlineshop.service.fx.CurrencyService;
import db2.onlineshop.service.fx.entity.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class BasicCurrencyService implements CurrencyService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private String defaultCurrency;
    private String url;
    private String pattern;

    private RestTemplate restTemplate = new RestTemplate();

    private volatile Map<String, Double> cache;

    @Override
    public double exchange(double value, String code) {
        if (code.equalsIgnoreCase(defaultCurrency)) {
            return value;
        }
        double rate = getRate(code);
        double result = value / rate;

        return result;
    }

    @PostConstruct
    @Scheduled(fixedDelayString = "${currency.schedule.fixedDelay}", initialDelayString = "${currency.schedule.initialDelay}")
    public void refreshCache() {
        Map<String, Double> result = getAll();
        log.debug("refreshCache:result.size={}", result.size());

        cache = result;
    }

    private double getRate(String code) {
        double result = cache.get(code);
        log.debug("getRate:result={}", result);

        return result;
    }

    private Map<String, Double> getAll() {
        long startTime = System.currentTimeMillis();
        String uri = getUri();
        String dateParam = getDateParam();
        Currency[] response = restTemplate.getForObject(uri, Currency[].class, dateParam);
        log.trace("getAll:response={}", response);

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

    @Value("${currency.default}")
    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
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
