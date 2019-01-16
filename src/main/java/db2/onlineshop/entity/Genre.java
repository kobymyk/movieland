package db2.onlineshop.entity;

public class Genre {
    private int id;
    private int movieId;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMovieId() {
        return movieId;
    }

    public Genre() {
    }

    public Genre(int id, int movieId, String name) {
        this.id = id;
        this.movieId = movieId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", name='" + name + '\'' +
                '}';
    }
}
