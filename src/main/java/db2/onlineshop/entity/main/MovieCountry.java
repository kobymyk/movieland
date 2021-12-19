package db2.onlineshop.entity.main;

import db2.onlineshop.entity.common.Country;

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
    @Column(name="country_code")
    private String countryCode;

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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "MovieCountry{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", countryCode=" + countryCode +
                '}';
    }
}
