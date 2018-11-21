package db2.onlineshop.web.transform;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonConverter<T> {
    String toJson(T anyObject) throws JsonProcessingException;
}
