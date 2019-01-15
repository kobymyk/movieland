package db2.onlineshop.web.data.mapper;

import db2.onlineshop.entity.Rating;
import db2.onlineshop.web.data.RatingResponse;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class RatingMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public List<RatingResponse> mapList(List<Rating> list) {
        List<RatingResponse> result = list.stream()
                .map(o -> mapObject(o))
                .collect(Collectors.toList());

        return result;
    }

    public RatingResponse mapObject(Rating object) {
        RatingResponse result = modelMapper.map(object, RatingResponse.class);

        return result;
    }
}
