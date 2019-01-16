package db2.onlineshop.web.data.mapper;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseMapper<T, O> {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ModelMapper modelMapper = new ModelMapper();

    private final Class<O> type;

    public ResponseMapper(Class<O> type) {
        this.type = type;
    }

    public List<O> mapList(List<T> list) {
        List<O> result = list.stream()
                .map(o -> mapObject(o))
                .collect(Collectors.toList());
        log.info("mapList:result={}", result);

        return result;
    }

    public O mapObject(T object) {
        O result = modelMapper.map(object, type);
        log.info("mapObject:result={}", result);

        return result;
    }
}
