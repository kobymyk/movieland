package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.impl.BasicGenreService;
import db2.onlineshop.service.impl.BasicMovieService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
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
    @Mock
    private BasicGenreService genreService;

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
    public void getById() throws Exception {
        final int id = 1;
        //when(movieService.getById(id, null)).thenReturn(mockMovies().get(0));
        //when(genreService.getByMovie(id)).thenReturn(mockGenres());

        mockMvc.perform(get("/v1/movie/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.genres[0].name", is("имя 1")))
                .andExpect(jsonPath("$.nameNative", is("name 1")));

        verify(movieService, times(1)).getById(id, null);
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
        Movie movie = null;
        List<Movie> result = new ArrayList<>();

        movie = new Movie();
        movie.setId(1);
        movie.setName("имя 1");
        movie.setNameNative("name 1");
        movie.setPicturePath("path/1");
        movie.setYearOfRelease(1001);
        movie.setRating(1.01);
        movie.setPrice(10.1);
        result.add(movie);

        movie = new Movie();
        movie.setId(2);
        movie.setName("имя 2");
        movie.setNameNative("name 2");
        movie.setPicturePath("path/2");
        movie.setYearOfRelease(2002);
        movie.setRating(2.02);
        movie.setPrice(20.2);
        result.add(movie);

        movie = new Movie();
        movie.setId(3);
        movie.setName("имя 3");
        movie.setNameNative("name 3");
        movie.setPicturePath("path/3");
        movie.setYearOfRelease(3003);
        movie.setRating(3.03);
        movie.setPrice(30.3);
        result.add(movie);

        return result;
    }

    private List<Genre> mockGenres() {
        List<Genre> result = Arrays.asList(
                new Genre(1, "genre 1"),
                new Genre(2, "genre 2"));

        return result;
    }
}