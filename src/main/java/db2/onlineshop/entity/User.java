package db2.onlineshop.entity;

import db2.onlineshop.service.security.entity.Role;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    private int id;
    @Column(name = "name")
    private String nickname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
