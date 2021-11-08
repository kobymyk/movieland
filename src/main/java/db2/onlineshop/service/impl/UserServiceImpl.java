package db2.onlineshop.service.impl;

import db2.onlineshop.dao.main.UserRepository;
import db2.onlineshop.entity.main.User;
import db2.onlineshop.service.UserService;
import db2.onlineshop.service.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private UserRepository userRepository;
    //private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }

    @Override
    public void resister(User user) {
        //todo:
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public String authenticate(String email, String password) {
        User user = findByEmail(email);
        Authentication request = new UsernamePasswordAuthenticationToken(email, password);
        Authentication result = authenticationManager.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(result);

        return tokenProvider.createToken(email, user.getRole().name());
    }
}
