package db2.onlineshop.entity.compound;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@MappedSuperclass
public class Rating {
    @Id
    private int id;
    @NaturalId
    @Column(name = "user_id")
    private int userId;
    @Column(nullable = false)
    private double rating;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                ", userId=" + userId +
                ", rating=" + rating +
                '}';
    }
}
