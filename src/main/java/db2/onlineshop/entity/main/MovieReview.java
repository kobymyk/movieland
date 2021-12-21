package db2.onlineshop.entity.main;


import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "review")
public class MovieReview extends Review {
    @NaturalId
    @Column(name = "movie_id")
    private int movieId;

}
