package db2.onlineshop.dao.rest;

import db2.onlineshop.dao.CurrencyDao;
import db2.onlineshop.entity.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Repository
public class RestCurrencyDao implements CurrencyDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange";

    private HttpEntity<?> request;

    private RestTemplate restTemplate;
    // todo: String url
    public RestCurrencyDao() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        request = new HttpEntity<>(headers);
    }

    @Override
    public Currency get(String code) {
        String uri = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("valcode", code)
                //.queryParam("date", "20181204")
                .queryParam("json").toUriString();
        log.trace("get:uri={}", uri);
        ResponseEntity<List<Currency>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<List<Currency>>() {}
        );
        // todo: responseEntity.getStatusCode() == HttpStatus.OK
        Currency result = response.getBody().get(0);
        log.trace("get:result={}", result);

        return result;
    }

    @Override
    // todo: cache
    public List<Currency> getAll() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("json");
        ResponseEntity<List<Currency>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<List<Currency>>() {}
        );

        return response.getBody();
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
