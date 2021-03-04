package db2.onlineshop.web.handler;

import db2.onlineshop.entity.main.User;
import db2.onlineshop.service.security.entity.Role;
import db2.onlineshop.service.security.exception.AuthenticationException;
import db2.onlineshop.service.security.holder.SecurityHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Service
public class PermissionInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    // todo: move to SecurityInterceptor
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        User user = SecurityHolder.get();
        HandlerMethod method = (HandlerMethod) handler;
        Permission annotation = method.getMethodAnnotation(Permission.class);
        if (annotation == null) {
            log.debug("preHandle:annotation is null");
            return true;
        }
        if (user != null) {
            Role role = user.getRole();
            if (Arrays.stream(annotation.roles()).anyMatch(role::equals)) {
                log.debug("preHandle:return true");
                return true;
            }
        }

        throw new AuthenticationException("Invalid role");
    }

}
