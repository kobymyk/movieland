package db2.onlineshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "movie_country")
public class MovieCountry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movieCountrySequence")
    @SequenceGenerator(name = "movieCountrySequence", sequenceName = "movie_country_seq", allocationSize = 1)
    private int id;
    @Column(name = "movie_id")
    private int movieId;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="country_id")
    private Country country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "MovieCountry{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", country=" + country +
                '}';
    }
}
