package db2.onlineshop.entity;

public enum SortOrder {
    ASC, DESC;

    public static SortOrder getValue(String text) {
        for (SortOrder value : values()) {
            if (text.equalsIgnoreCase(value.toString())) {
                return value;
            }
        }
        return null;
    }
}
