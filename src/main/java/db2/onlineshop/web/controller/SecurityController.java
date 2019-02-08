package db2.onlineshop.web.controller;

import db2.onlineshop.service.security.SecurityService;
import db2.onlineshop.service.security.entity.Session;
import db2.onlineshop.web.data.LoginRequest;
import db2.onlineshop.web.data.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class SecurityController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private SecurityService securityService;

    @PostMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public LoginResponse login(@RequestBody LoginRequest request) {
        long startTime = System.currentTimeMillis();
        String email = request.getEmail();
        log.info("login:email={}", email);

        Session session = securityService.login(email, request.getPassword());
        LoginResponse result = new LoginResponse();
        result.setNickname(session.getUser().getNickname());
        result.setUuid(session.getToken());
        log.info("login:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public void logout(@RequestHeader(value = "uuid", required = false) String token) {
        log.info("logout:token={}", token);
        securityService.logout(token);
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
