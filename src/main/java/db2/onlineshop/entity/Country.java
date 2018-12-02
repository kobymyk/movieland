package db2.onlineshop.entity;

// Lookup template
public class Country {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country() {
    }

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
