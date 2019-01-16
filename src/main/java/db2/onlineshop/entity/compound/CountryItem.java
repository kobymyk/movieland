package db2.onlineshop.entity.compound;

public class CountryItem {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CountryItem() {
    }

    public CountryItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "CountryItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
