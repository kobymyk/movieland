package db2.onlineshop.service.security.holder;

import db2.onlineshop.entity.UserLogin;

public class SecurityHolder {
    private static final ThreadLocal<UserLogin> threadLocal = new ThreadLocal<>();

    public static UserLogin get() {
        return threadLocal.get();
    }

    public static void set(UserLogin user) {
        threadLocal.set(user);
    }

    public static void clear() {
        threadLocal.remove();
    }
}