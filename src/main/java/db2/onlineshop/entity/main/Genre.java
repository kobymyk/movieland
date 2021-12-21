package db2.onlineshop.entity.main;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    private int id;
    private String name;
    @ToString.Exclude
    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private Set<MovieGenre> movieGenres;
}
