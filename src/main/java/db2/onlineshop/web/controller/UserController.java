package db2.onlineshop.web.controller;

import db2.onlineshop.dao.main.UserRepository;
import db2.onlineshop.entity.main.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAnyAuthority('user:read', 'user:write')")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAnyAuthority('user:read', 'user:write')")
    public User getById(@PathVariable int id) {
        return userRepository.getOne(id);
    }
}
