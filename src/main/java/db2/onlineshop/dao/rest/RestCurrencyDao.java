package db2.onlineshop.dao.rest;

import db2.onlineshop.dao.CurrencyDao;
import db2.onlineshop.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class RestCurrencyDao implements CurrencyDao {
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
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("valcode", code)
                .queryParam("json");
        ResponseEntity<Currency> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                request,
                Currency.class
        );
        // todo: responseEntity.getStatusCode() == HttpStatus.OK
        return response.getBody();
    }

    @Override
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
