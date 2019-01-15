package db2.onlineshop.web.data.mapper;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.web.data.MovieResponse;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public List<MovieResponse> mapList(List<Movie> movies) {
        List<MovieResponse> result = movies.stream()
                .map(o -> mapObject(o))
                .collect(Collectors.toList());

        return result;
    }

    public MovieResponse mapObject(Movie movie) {
        MovieResponse result = modelMapper.map(movie, MovieResponse.class);

        return result;
    }
}
