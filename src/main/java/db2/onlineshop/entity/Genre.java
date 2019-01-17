package db2.onlineshop.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Genre {
    @Id
    protected int id;
    protected String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Genre() {
    }

    public Genre(int id, String name) {
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
