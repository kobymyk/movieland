package db2.onlineshop.service.security.impl;

import db2.onlineshop.entity.User;
import db2.onlineshop.service.UserService;
import db2.onlineshop.service.security.SecurityService;
import db2.onlineshop.service.security.entity.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DefaultSecurityService implements SecurityService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private UserService userService;
    private Long maxDuration;
    // index by token
    private final Map<String, Session> sessions = new ConcurrentHashMap<>();

    @Override
    public Optional<User> getUser(String token) {
        Optional<Session> session = get(token);
        if (session.isPresent()) {
            User result = session.get().getUser();
            return Optional.of(result);
        }
        log.debug("getUser:empty");

        return Optional.empty();
    }

    private User authenticate(String email, String password) {
        Optional<User> anyUser = userService.getByEmail(email);
        if (!anyUser.isPresent()) {
            // todo: custom exception, ?response
            throw new RuntimeException("Invalid user");
        }
        User result = anyUser.get();
        log.debug("getUser:id={}", result.getId());
        if (!password.equals(result.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return result;
    }

    @Override
    public Session login(String email, String password) {
        Session result = null;
        User user = authenticate(email, password);

        Optional<Session> anySession = find(user);
        if (anySession.isPresent()) {
            result = anySession.get();
            result.setExpireDate(getExpireDate());
        } else {
            result = create(user);
        }
        log.trace("login:", result);

        return result;
    }

    @Override
    public void logout(String token) {
        log.info("logout", token);
        sessions.remove(token);
    }

    private Optional<Session> find(User user) {
        for (Session session : sessions.values()) {
            String email = session.getUser().getEmail();
            if (email.equals(user.getEmail()) ) {
                log.debug("find:email={}", email);
                return Optional.of(session);
            }
        }
        log.debug("find:empty");

        return Optional.empty();
    }

    private LocalDateTime getExpireDate() {
        LocalDateTime result = LocalDateTime.now().plusMinutes(maxDuration);

        return result;
    }

    private Session create(User user) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expireDate = getExpireDate();

        Session result = new Session(user, token, expireDate);
        log.trace("create:result={}", result);

        return result;
    }

    private Optional<Session> get(String token) {
        if (sessions.containsKey(token)) {
            Session result = sessions.get(token);

            if (result.getExpireDate().isBefore(LocalDateTime.now())) {
                sessions.remove(token);
            } else {
                log.debug("get:result={}", result);

                return Optional.of(result);
            }
        }
        log.debug("get:empty");

        return Optional.empty();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Value("${session.maxDuration}")
    public void setMaxDuration(Long maxDuration) {
        this.maxDuration = maxDuration;
    }
}
