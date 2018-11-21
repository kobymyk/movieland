package db2.onlineshop.web.transform.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db2.onlineshop.web.transform.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultJsonConverter implements JsonConverter {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public String toJson(Object anyObject) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("toJson::start");
        String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(anyObject);
        log.info("toJson::end");

        return result;
    }
}
