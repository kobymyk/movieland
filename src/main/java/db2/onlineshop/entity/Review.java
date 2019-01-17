package db2.onlineshop.entity;

import javax.persistence.*;

@MappedSuperclass
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviewSequence")
    @SequenceGenerator(name = "reviewSequence", sequenceName = "review_seq", allocationSize = 1)
    protected int id;
    @Column(name = "review_text")
    protected String text;
    @Column(name = "user_id")
    protected int userId;

    public Review() {
    }

    public Review(int id, String text, int userId) {
        this.id = id;
        this.text = text;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() { return userId; }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ReviewItem{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", userId=" + userId +
                '}';
    }
}
