package db2.onlineshop.entity.main;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movieSequence")
    @SequenceGenerator(name = "movieSequence", sequenceName = "movie_seq", allocationSize = 1)
    private int id;
    @Column(name = "release_year")
    private int yearOfRelease;
    private String name;
    @Column(name = "name_native")
    private String nameNative;
    @ToString.Exclude
    private String description;
    @ToString.Exclude
    @Column(name = "picture_path")
    private String picturePath;
    private double rating;
    private double price;
    @Column(name = "country_code")
    private String countryCode;
    //@ToString.Exclude
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private Set<MovieGenre> movieGenres;
    @Transient
    private List<Review> reviews;
}
