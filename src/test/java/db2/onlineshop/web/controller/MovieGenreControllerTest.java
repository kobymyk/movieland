package db2.onlineshop.web.controller;

import db2.onlineshop.service.GenreService;
import org.junit.Before;
import org.junit.Ignore;
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

public class MovieGenreControllerTest {
    @Mock
    private GenreService genreService;
    @InjectMocks
    private GenreController genreController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(genreController).build();
    }

    @Test
    @Ignore
    public void getAll() throws Exception {
        when(genreService.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/v1/genre"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        verify(genreService, times(1)).getAll();
        verifyNoMoreInteractions(genreService);
    }

}