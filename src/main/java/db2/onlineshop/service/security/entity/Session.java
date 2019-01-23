package db2.onlineshop.service.security.entity;


import db2.onlineshop.entity.UserLogin;

import java.time.LocalDateTime;

public class Session {
    private UserLogin user;
    private String token;

    private LocalDateTime expireDate;

    public Session(UserLogin user, String token, LocalDateTime expireDate) {
        this.user = user;
        this.token = token;
        this.expireDate = expireDate;
    }

    public UserLogin getUser() {
        return user;
    }

    public void setUser(UserLogin user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "Session{" +
                "user=" + user +
                ", token='" + token + '\'' +
                ", expireDate=" + expireDate +
                '}';
    }
}
