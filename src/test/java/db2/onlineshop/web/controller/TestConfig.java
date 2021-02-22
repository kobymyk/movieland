package db2.onlineshop.web.controller;

import db2.onlineshop.service.*;
import db2.onlineshop.service.security.SecurityService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = {"db2.onlineshop.web.controller"})
public class TestConfig {
    @Bean
    public CountryService countryService() {
        return Mockito.mock(CountryService.class);
    }
    @Bean
    public GenreService genreService() {
        return Mockito.mock(GenreService.class);
    }
    @Bean
    public RatingService ratingService() {
        return Mockito.mock(RatingService.class);
    }
    @Bean
    ReviewService reviewService() {
        return Mockito.mock(ReviewService.class);
    }
    @Bean
    SecurityService securityService() {
        return Mockito.mock(SecurityService.class);
    }
    @Bean
    @Primary
    public MovieService movieServiceSpy(MovieService movieService) {
        return Mockito.spy(movieService);
    }
}
