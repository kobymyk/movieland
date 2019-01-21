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
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    protected User user;

    public Review() {
    }

    public Review(int id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ReviewItem{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }
}
