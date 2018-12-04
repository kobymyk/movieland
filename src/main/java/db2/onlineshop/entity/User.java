package db2.onlineshop.entity;

// Lookup template
public class User {
    private int id;
    private String nickname;

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public User() {
    }

    public User(int id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
