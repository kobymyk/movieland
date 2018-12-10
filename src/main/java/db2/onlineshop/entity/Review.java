package db2.onlineshop.entity;


public class Review {
    private int id;
    private int movieId;

    private String text;
    private User user;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
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

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Review() {
    }

    public Review(int id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Review{id=" + id + '}';
    }
}
