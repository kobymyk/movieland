package db2.onlineshop.service.security.impl;

import db2.onlineshop.dao.UserLoginDao;
import db2.onlineshop.entity.UserLogin;
import db2.onlineshop.service.security.SecurityService;
import db2.onlineshop.service.security.entity.Session;
import db2.onlineshop.service.security.exception.AuthenticationException;
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

    private UserLoginDao loginDao;
    private Long maxDuration;
    // index by token
    private final Map<String, Session> sessions = new ConcurrentHashMap<>();

    @Override
    public Optional<UserLogin> getUserLogin(String token) {
        Optional<Session> session = get(token);
        if (session.isPresent()) {
            UserLogin result = session.get().getUser();
            return Optional.of(result);
        }
        log.debug("getUser:empty");

        return Optional.empty();
    }

    @Override
    public Session login(String email, String password) {
        Session result = null;
        UserLogin user = authenticate(email, password);

        Optional<Session> anySession = find(user);
        if (anySession.isPresent()) {
            result = anySession.get();
            result.setExpireDate(getExpireDate());
        } else {
            result = create(user);
        }
        log.trace("login:result={}", result);

        return result;
    }

    @Override
    public void logout(String token) {
        log.info("logout", token);
        sessions.remove(token);
    }

    private UserLogin authenticate(String email, String password) {
        Optional<UserLogin> anyUser = loginDao.getByKey("email", email);
        if (!anyUser.isPresent()) {
            throw new AuthenticationException("Invalid user");
        }
        UserLogin result = anyUser.get();
        if (!password.equals(result.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }
        log.debug("authenticate:result={}", result);

        return result;
    }

    private Optional<Session> find(UserLogin user) {
        for (Session result : sessions.values()) {
            String email = result.getUser().getEmail();
            if (email.equals(user.getEmail()) ) {
                log.debug("find:email={}", email);
                return Optional.of(result);
            }
        }
        log.debug("find:empty");

        return Optional.empty();
    }

    private LocalDateTime getExpireDate() {
        LocalDateTime result = LocalDateTime.now().plusMinutes(maxDuration);

        return result;
    }

    private Session create(UserLogin user) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expireDate = getExpireDate();

        Session result = new Session(user, token, expireDate);
        sessions.put(token, result);
        log.trace("create:result={}", result);

        return result;
    }

    private Optional<Session> get(String token) {
        if (sessions.containsKey(token)) {
            Session result = sessions.get(token);

            LocalDateTime expireDate = result.getExpireDate();
            if (expireDate.isBefore(LocalDateTime.now())) {
                log.debug("get:expireDate={}", expireDate);
                sessions.remove(token);
            } else {
                log.trace("get:result={}", result);

                return Optional.of(result);
            }
        }
        log.debug("get:empty");

        return Optional.empty();
    }

    @Autowired
    public void setLoginDao(UserLoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @Value("${session.maxDuration}")
    public void setMaxDuration(Long maxDuration) {
        this.maxDuration = maxDuration;
    }
}
