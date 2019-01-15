package db2.onlineshop.web.data;

public class RatingResponse {
    private double rating;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "rating=" + rating +
                '}';
    }
}
