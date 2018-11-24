package db2.onlineshop.entity;

public class Genre {
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre() {
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
