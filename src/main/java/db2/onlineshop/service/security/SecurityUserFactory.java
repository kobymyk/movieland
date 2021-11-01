package db2.onlineshop.service.security;

import db2.onlineshop.entity.main.User;
import db2.onlineshop.service.security.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class SecurityUserFactory {
    public SecurityUserFactory() {
    }

    public static SecurityUser create(User user) {
        return new SecurityUser(
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthority(user.getRole())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(Role role) {
        List<GrantedAuthority> result = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        result.add(authority);
        return result;
    }
}
