package db2.onlineshop.web.data;

import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAddRequest {
    private String name;
    private String nameNative;

    private String description;
    private String picturePath;

    private int yearOfRelease;
    private Double price;

    private List<Integer> countries;
    private List<Integer> genres;

    public Movie getMovie() {
        Movie result = new Movie();
        result.setName(name);
        result.setNameNative(nameNative);
        result.setPicturePath(picturePath);
        result.setDescription(description);

        result.setYearOfRelease(yearOfRelease);
        result.setPrice(price);

        result.setCountries(getCountries());
        result.setGenres(getGenres());

        return result;
    }

    public List<Country> getCountries(){
        List<Country> result = new ArrayList<>();
        for (Integer countryId : countries) {
            Country country = new Country(countryId, null);
            result.add(country);
        }

        return result;
    }

    public List<Genre> getGenres(){
        List<Genre> result = new ArrayList<>();
        for (Integer genreId : genres) {
            Genre genre = new Genre(genreId, null);
            result.add(genre);
        }

        return result;
    }

    @Override
    public String toString() {
        return "MovieAddRequest{" +
                "name='" + name + '\'' +
                ", nameNative='" + nameNative + '\'' +
                ", description='" + description + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", price=" + price +
                ", countries=" + countries +
                ", genres=" + genres +
                '}';
    }
}
