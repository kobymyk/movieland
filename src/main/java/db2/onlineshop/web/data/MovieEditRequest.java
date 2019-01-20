package db2.onlineshop.web.data;

import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieEditRequest {
    private String nameRussian;
    private String nameNative;
    private String picturePath;

    private List<Integer> countries;
    private List<Integer> genres;

    public Movie getMovie() {
        Movie result = new Movie();
        result.setName(nameRussian);
        result.setNameNative(nameNative);
        result.setPicturePath(picturePath);

        result.setCountries(getCountries());
        result.setMovieGenres(getGenres());

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

    public void setNameRussian(String nameRussian) {
        this.nameRussian = nameRussian;
    }

    public void setNameNative(String nameNative) {
        this.nameNative = nameNative;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void setCountries(List<Integer> countries) {
        this.countries = countries;
    }

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "MovieAddRequest{" +
                "nameRussian='" + nameRussian + '\'' +
                ", nameNative='" + nameNative + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", countries=" + countries +
                ", genres=" + genres +
                '}';
    }
}
