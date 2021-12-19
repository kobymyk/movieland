package db2.onlineshop.web.data;

import db2.onlineshop.entity.common.Country;
import db2.onlineshop.entity.main.Genre;
import db2.onlineshop.entity.main.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAddRequest {
    private String nameRussian;
    private String nameNative;
    private int yearOfRelease;
    private String description;
    private Double price;
    private String picturePath;

    private List<String> countries;
    private List<Integer> genres;

    public Movie getMovie() {
        Movie result = new Movie();
        result.setName(nameRussian);
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
        for (String countryCode : countries) {
            Country item = new Country();
            item.setCountryCode(countryCode);
            result.add(item);
        }

        return result;
    }

    public List<Genre> getGenres(){
        List<Genre> result = new ArrayList<>();
        for (Integer genreId : genres) {
            Genre item = new Genre(genreId, null);
            result.add(item);
        }

        return result;
    }

    public void setNameRussian(String nameRussian) {
        this.nameRussian = nameRussian;
    }

    public void setNameNative(String nameNative) {
        this.nameNative = nameNative;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void setCountries(List<String> countries) {
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
                ", description='" + description + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", price=" + price +
                ", countries=" + countries +
                ", genres=" + genres +
                '}';
    }
}
