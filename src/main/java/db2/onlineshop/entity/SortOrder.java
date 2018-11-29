package db2.onlineshop.entity;

import java.util.HashMap;
import java.util.Map;

public enum SortOrder {
    ASC("asc"),
    DESC("desc");

    private String direction;
    private static final Map<String, SortOrder> lookup = new HashMap<>();

    static {
        for (SortOrder value : SortOrder.values()) {
            lookup.put(value.direction, value);
        }
    }

    SortOrder(String direction) {
        this.direction = direction;
    }

    public static SortOrder getEnum(String direction) {
        SortOrder result = lookup.get(direction.toLowerCase());
        if (result == null) {
            throw new IllegalArgumentException();
        }

        return result;
    }
}
