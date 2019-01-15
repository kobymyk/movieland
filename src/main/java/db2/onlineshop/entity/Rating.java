package db2.onlineshop.entity;

public class Rating {
    private int movieId;
    private User user;
    private double rating;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "movieId=" + movieId +
                ", user=" + user +
                ", rating=" + rating +
                '}';
    }
}
