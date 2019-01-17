package db2.onlineshop.entity;

public class Ordering {
    private String field;
    // todo: ascending boolean
    private SortOrder direction;

    public Ordering() {
    }

    public Ordering(String field, SortOrder direction) {
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
