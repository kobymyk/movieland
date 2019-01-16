package db2.onlineshop.web.data;

public class RatingRequest {
    private double rating;

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "RatingRequest{" +
                "rating=" + rating +
                '}';
    }
}
