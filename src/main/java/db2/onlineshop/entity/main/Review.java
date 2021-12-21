package db2.onlineshop.entity.main;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviewSequence")
    @SequenceGenerator(name = "reviewSequence", sequenceName = "review_seq", allocationSize = 1)
    protected int id;
    @Column(name = "review_text")
    protected String text;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="user_id")
    protected User user;

}
