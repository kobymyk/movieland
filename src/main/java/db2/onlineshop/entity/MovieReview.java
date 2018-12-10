package db2.onlineshop.entity;


public class MovieReview {
    private int movieId;

    private Review review;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "MovieReview{" +
                "movieId=" + movieId +
                ", review=" + review +
                '}';
    }
}
