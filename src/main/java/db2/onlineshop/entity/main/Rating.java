package db2.onlineshop.entity.main;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
@MappedSuperclass
public class Rating {
    @Id
    protected int id;
    @NaturalId
    @Column(name = "user_id")
    protected int userId;
    @Column(nullable = false)
    protected double rating;

}
