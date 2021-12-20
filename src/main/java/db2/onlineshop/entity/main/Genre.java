package db2.onlineshop.entity.main;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private Set<MovieGenre> movieGenres;

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

    public Set<MovieGenre> getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(Set<MovieGenre> movieGenres) {
        this.movieGenres = movieGenres;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
