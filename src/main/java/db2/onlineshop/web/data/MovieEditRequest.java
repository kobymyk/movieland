package db2.onlineshop.web.data;

import db2.onlineshop.entity.main.Genre;
import db2.onlineshop.entity.main.Movie;
import db2.onlineshop.entity.main.MovieGenre;

import java.util.ArrayList;
import java.util.List;

public class MovieEditRequest {
    private String nameRussian;
    private String nameNative;
    private String picturePath;

    private List<Integer> genres;

    public Movie getMovie() {
        Movie result = new Movie();
        result.setName(nameRussian);
        result.setNameNative(nameNative);
        result.setPicturePath(picturePath);
        //todo:
        //result.setGenres(getGenres());

        return result;
    }

    public List<MovieGenre> mapMovieGenres() {
        List<MovieGenre> result = new ArrayList<>();
        for (Integer genreId : genres) {
            MovieGenre movieGenre = new MovieGenre();
            movieGenre.setGenre(new Genre());
            movieGenre.getGenre().setId(genreId);

            result.add(movieGenre);
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

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "MovieAddRequest{" +
                "nameRussian='" + nameRussian + '\'' +
                ", nameNative='" + nameNative + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", genres=" + genres +
                '}';
    }
}
