package db2.onlineshop.service.security.impl;

import db2.onlineshop.dao.main.UserRepository;
import db2.onlineshop.entity.main.User;
import db2.onlineshop.service.security.SecurityService;
import db2.onlineshop.service.security.entity.Session;
import db2.onlineshop.service.security.exception.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Value("${session.maxDuration:60}")
    private Long maxDuration;
    private final Map<String, Session> sessions = new ConcurrentHashMap<>();

    private UserRepository userRepository;

    public DefaultSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUser(String token) {
      /*  Optional<Session> session = getSession(token);
        if (session.isPresent()) {
            User result = session.get().getUser();
            return Optional.of(result);
        }
        log.debug("getUser:empty");*/

        return Optional.empty();
    }

    @Override
    public Session login(String email, String password) {
        Session result;
        Optional<User> user = authenticate(email, password);

       /* Optional<Session> session = find(user);
        if (session.isPresent()) {
            result = session.get();
            result.setExpireDate(getExpireDate());
        } else {
            result = create(user);
        }
        log.trace("login:result={}", result);*/

        return null;
    }

    @Override
    public void logout(String token) {
        log.info("logout:token={}", token);
        sessions.remove(token);
    }

    private Optional<User> authenticate(String email, String password) {
/*        Optional<User> result = userRepository.findByEmail(email);
        if (result == null) {
            throw new AuthenticationException("Invalid user");
        }
        if (!password.equals(result.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }
        log.debug("authenticate:result={}", result);*/

        return Optional.empty();
    }

    private Optional<Session> find(User user) {
      /*  for (Session result : sessions.values()) {
            String email = result.getUser().getEmail();
            if (email.equals(user.getEmail()) ) {
                log.debug("find:email={}", email);
                return Optional.of(result);
            }
        }
        log.debug("find:empty");*/

        return Optional.empty();
    }

    private LocalDateTime getExpireDate() {
        return LocalDateTime.now().plusMinutes(maxDuration);
    }

    private Session create(User user) {
       /* String token = UUID.randomUUID().toString();
        LocalDateTime expireDate = getExpireDate();

        Session result = new Session(user, token, expireDate);
        sessions.put(token, result);
        log.trace("create:result={}", result);
*/
        return null;
    }

    private Optional<Session> getSession(String token) {
       /* if (sessions.containsKey(token)) {
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
        log.debug("get:empty");*/

        return Optional.empty();
    }

}
