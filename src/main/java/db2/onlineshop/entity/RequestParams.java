package db2.onlineshop.entity;

public class RequestParams {
    private String field;
    private SortOrder direction;
    // todo: ?enum
    private String currency;

    public RequestParams() {
    }

    public RequestParams(String field, SortOrder direction) {
        this.field = field;
        this.direction = direction;
    }

    public String getField() {
        return field;
    }

    public SortOrder getDirection() {
        return direction;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setDirection(SortOrder direction) {
        this.direction = direction;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
