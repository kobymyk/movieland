package db2.onlineshop.web.data;

import db2.onlineshop.entity.compound.CountryItem;
import db2.onlineshop.entity.compound.GenreItem;
import db2.onlineshop.entity.compound.MovieItems;

import java.util.ArrayList;
import java.util.List;

public class MovieAddRequest {
    private String nameRussian;
    private String nameNative;
    private int yearOfRelease;
    private String description;
    private Double price;
    private String picturePath;

    private List<Integer> countries;
    private List<Integer> genres;

    public MovieItems getMovie() {
        MovieItems result = new MovieItems();
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

    public List<CountryItem> getCountries(){
        List<CountryItem> result = new ArrayList<>();
        for (Integer countryId : countries) {
            CountryItem item = new CountryItem(countryId, null);
            result.add(item);
        }

        return result;
    }

    public List<GenreItem> getGenres(){
        List<GenreItem> result = new ArrayList<>();
        for (Integer genreId : genres) {
            GenreItem item = new GenreItem(genreId, null);
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
                ", description='" + description + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", price=" + price +
                ", countries=" + countries +
                ", genres=" + genres +
                '}';
    }
}
