package db2.onlineshop.entity;

public class SortParam {
    private String field;
    private SortOrder direction;

    public SortParam() {
    }

    public SortParam(String field, SortOrder direction) {
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
}
