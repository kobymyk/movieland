package db2.onlineshop.web.transform;

public interface JsonConverter<T> {
    String toJson(T anyObject);
}
