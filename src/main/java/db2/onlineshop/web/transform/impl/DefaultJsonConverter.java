package db2.onlineshop.web.transform.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db2.onlineshop.web.transform.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultJsonConverter implements JsonConverter {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public String toJson(Object anyObject) {
        long startTime = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("toJson:anyObject={}", anyObject.getClass().getName());

        String result = null;
        try {
            result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(anyObject);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        log.info("toJson:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }
}
