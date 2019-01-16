package db2.onlineshop.entity.compound;

public class GenreItem {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GenreItem() {
    }

    public GenreItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "GenreItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
