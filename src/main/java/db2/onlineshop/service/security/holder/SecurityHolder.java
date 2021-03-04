package db2.onlineshop.service.security.holder;

import db2.onlineshop.entity.main.User;

public class SecurityHolder {
    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static User get() {
        return threadLocal.get();
    }

    public static void set(User user) {
        threadLocal.set(user);
    }

    public static void clear() {
        threadLocal.remove();
    }
}