package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Genre;
import db2.onlineshop.service.impl.BasicGenreService;
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

public class GenreControllerTest {
    @Mock
    private BasicGenreService genreService;
    @InjectMocks
    private GenreController genreController;
    private MockMvc mockMvc;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(genreController).build();
    }

    @Test
    public void getAll() throws Exception {
        List<Genre> genres = mockGenres();
        when(genreService.getAll()).thenReturn(genres);

        mockMvc.perform(get("/v1/genre"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("название 1")))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("название 2")));

        verify(genreService, times(1)).getAll();
        verifyNoMoreInteractions(genreService);
    }

    private List<Genre> mockGenres() {
        List<Genre> result = Arrays.asList(
                new Genre(1, "название 1"),
                new Genre(2, "название 2"),
                new Genre(3, "название 3"));

        return result;
    }

}