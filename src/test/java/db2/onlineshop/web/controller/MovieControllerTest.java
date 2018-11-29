package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.SortOrder;
import db2.onlineshop.entity.SortParam;
import db2.onlineshop.service.impl.BasicMovieService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class MovieControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    MovieController movieController;
    @Mock
    private BasicMovieService movieService; // will be injected into movieController

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(movieController).build();
    }

    @Test
    public void getAll() throws Exception {
        List<Movie> movies = mockMovies();
        when(movieService.getAll(null)).thenReturn(movies);

        mockMvc.perform(get("/v1/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("имя 1")))
                .andExpect(jsonPath("$[0].nameNative", is("name 1")))
                .andExpect(jsonPath("$[0].yearOfRelease", is(1001)))
                .andExpect(jsonPath("$[0].rating", is(1.01)))
                .andExpect(jsonPath("$[0].price", is(10.1)))
                .andExpect(jsonPath("$[0].picturePath", is("path/1")))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("имя 2")))
                .andExpect(jsonPath("$[1].nameNative", is("name 2")))
                .andExpect(jsonPath("$[1].picturePath", is("path/2")))
                .andExpect(jsonPath("$[1].yearOfRelease", is(2002)))
                .andExpect(jsonPath("$[1].rating", is(2.02)))
                .andExpect(jsonPath("$[1].price", is(20.2)));

        verify(movieService, times(1)).getAll(null);
        verifyNoMoreInteractions(movieService);
    }

    @Test
    public void getRandom() throws Exception {
        List<Movie> movies = mockMovies();
        when(movieService.getRandom()).thenReturn(movies);

        mockMvc.perform(get("/v1/movie/random"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                // todo: 2x
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("имя 1")))
                .andExpect(jsonPath("$[0].nameNative", is("name 1")))
                .andExpect(jsonPath("$[0].yearOfRelease", is(1001)))
                .andExpect(jsonPath("$[0].rating", is(1.01)))
                .andExpect(jsonPath("$[0].price", is(10.1)))
                .andExpect(jsonPath("$[0].picturePath", is("path/1")));

        verify(movieService, times(1)).getRandom();
        verifyNoMoreInteractions(movieService);
    }

    @Test
    public void getByGenre() throws Exception {
        final int genreId = 1;
        List<Movie> movies = mockMovies();
        when(movieService.getByGenre(genreId)).thenReturn(movies);

        mockMvc.perform(get("/v1/movie/genre/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                // todo: 3x
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("имя 1")))
                .andExpect(jsonPath("$[0].nameNative", is("name 1")))
                .andExpect(jsonPath("$[0].yearOfRelease", is(1001)))
                .andExpect(jsonPath("$[0].rating", is(1.01)))
                .andExpect(jsonPath("$[0].price", is(10.1)))
                .andExpect(jsonPath("$[0].picturePath", is("path/1")));

        verify(movieService, times(1)).getByGenre(genreId);
        verifyNoMoreInteractions(movieService);
    }

    @Test
    public void getAllSorted() throws Exception {
        when(movieService.getAll(any())).thenReturn(mockMovies());

        mockMvc.perform(get("/v1/movie?rating=asc")).andExpect(status().isOk());
        mockMvc.perform(get("/v1/movie?rating=desc")).andExpect(status().isOk());
        mockMvc.perform(get("/v1/movie?price=asc")).andExpect(status().isOk());
        mockMvc.perform(get("/v1/movie?price=desc")).andExpect(status().isOk());
    }

    private List<Movie> mockMovies() {
        List<Movie> result = Arrays.asList(
                new Movie(1, "имя 1", "name 1", 1001, 1.01, 10.1, "path/1"),
                new Movie(2, "имя 2", "name 2", 2002, 2.02, 20.2, "path/2"),
                new Movie(3, "имя 3", "name 3", 3003, 3.03, 30.3, "path/3"));

        return result;
    }

}