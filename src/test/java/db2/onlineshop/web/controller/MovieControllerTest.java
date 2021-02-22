package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class MovieControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    MovieController movieController;
    @Mock
    private MovieService movieService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(movieController).build();
    }

    @Test
    public void getAll() throws Exception {
        when(movieService.getAll(any())).thenReturn(new ArrayList<>());
        //todo: CallRealMethod
        //when(movieService.getAll(any())).thenCallRealMethod();

        mockMvc.perform(get("/v1/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        verify(movieService, times(1)).getAll(null);
        verifyNoMoreInteractions(movieService);
    }

    @Test
    public void getByGenre() throws Exception {
        when(movieService.getByGenre(anyInt())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/v1/movie/genre/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void getById() throws Exception {
        when(movieService.getById(eq(1), anyString())).thenReturn(new Movie());

        mockMvc.perform(get("/v1/movie/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void getAllSorted() throws Exception {
        when(movieService.getAll(any())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/v1/movie?rating=asc")).andExpect(status().isOk());
        mockMvc.perform(get("/v1/movie?rating=desc")).andExpect(status().isOk());
        mockMvc.perform(get("/v1/movie?price=asc")).andExpect(status().isOk());
        mockMvc.perform(get("/v1/movie?price=desc")).andExpect(status().isOk());
    }

}