package db2.onlineshop.web.data;

import db2.onlineshop.entity.compound.CountryItem;
import db2.onlineshop.entity.compound.GenreItem;
import db2.onlineshop.entity.compound.MovieItems;

import java.util.ArrayList;
import java.util.List;

public class MovieEditRequest {
    private String nameRussian;
    private String nameNative;
    private String picturePath;

    private List<Integer> countries;
    private List<Integer> genres;

    public MovieItems getMovie() {
        MovieItems result = new MovieItems();
        result.setName(nameRussian);
        result.setNameNative(nameNative);
        result.setPicturePath(picturePath);
        result.setCountries(getCountries());
        result.setGenres(getGenres());

        return result;
    }

    public List<CountryItem> getCountries(){
        List<CountryItem> result = new ArrayList<>();
        for (Integer countryId : countries) {
            CountryItem country = new CountryItem(countryId, null);
            result.add(country);
        }

        return result;
    }

    public List<GenreItem> getGenres(){
        List<GenreItem> result = new ArrayList<>();
        for (Integer genreId : genres) {
            GenreItem genre = new GenreItem(genreId, null);
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
