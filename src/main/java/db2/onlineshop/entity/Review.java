package db2.onlineshop.entity;


public class Review {
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

    public Review() {
    }

    public Review(int id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }
}
