package db2.onlineshop.web.handler;

import db2.onlineshop.entity.User;
import db2.onlineshop.service.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        String token = request.getHeader("uuid");
        if (token != null) {
            log.info("preHandle:token={}", token);
            Optional<User> anyUser = securityService.getUser(token);
            if (anyUser.isPresent()) {
                User user = anyUser.get();
                log.info("preHandle:email={}", user.getEmail());
            }
        }

        return true;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
