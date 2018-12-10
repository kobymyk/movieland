package db2.onlineshop.web.handler;

import db2.onlineshop.entity.User;
import db2.onlineshop.service.security.SecurityService;
import db2.onlineshop.service.security.holder.SecurityHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        String token = request.getHeader("uuid");

        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);

        if (token != null) {
            log.info("preHandle:token={}", token);
            Optional<User> anyUser = securityService.getUser(token);
            if (anyUser.isPresent()) {
                User user = anyUser.get();

                SecurityHolder.set(user);
                MDC.put("userId", Integer.toString(user.getId()));
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        MDC.remove("requestId");
        MDC.remove("userId");

        SecurityHolder.clear();
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
