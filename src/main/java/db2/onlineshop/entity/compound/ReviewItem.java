package db2.onlineshop.entity.compound;


import db2.onlineshop.entity.User;

public class ReviewItem {
    private int id;
    private String text;
    private User user;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
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

    public void setUser(User user) {
        this.user = user;
    }

    public ReviewItem() {
    }

    public ReviewItem(int id, String text, User user) {
        this.id = id;
        this.text = text;
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
