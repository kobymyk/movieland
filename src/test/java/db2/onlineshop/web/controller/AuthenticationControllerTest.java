package db2.onlineshop.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import db2.onlineshop.dao.main.UserRepository;
import db2.onlineshop.entity.main.User;
import db2.onlineshop.service.security.JwtTokenProvider;
import db2.onlineshop.service.security.entity.Role;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AuthenticationControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    AuthenticationController authenticationController;
    @Mock
    private AuthenticationManager manager;
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(authenticationController).build();
    }

    @Test
    public void login() throws Exception {
        User user = new User();
        user.setEmail("ronald.reynolds66@example.com");
        user.setPassword("paco");
        user.setRole(Role.ROLE_ADMIN);

        when(userRepository.findByEmail(any())).thenReturn(java.util.Optional.of(user));

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(user);

        mockMvc.perform(post("/v1/login").contentType(APPLICATION_JSON_VALUE)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE));
    }

    @Test
    public void getAuthorities_RoleAdmin() {
        //given
        User user = new User();
        user.setEmail("ronald.reynolds66@example.com");
        user.setPassword("paco");
        user.setRole(Role.ROLE_ADMIN);

        //when
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(user.getRole().getAuthorities());

        //then
        assertEquals(2, authorities.size());
        assertEquals("user:read", authorities.get(0).toString());
        assertEquals("user:write", authorities.get(1).toString());
    }

    @Test
    public void getAuthorities_RoleUser() {
        //given
        User user = new User();
        user.setEmail("user@example.com");
        user.setPassword("user");
        user.setRole(Role.ROLE_USER);

        //when
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(user.getRole().getAuthorities());

        //then
        assertEquals(1, authorities.size());
        assertEquals("user:read", authorities.get(0).toString());
    }
}