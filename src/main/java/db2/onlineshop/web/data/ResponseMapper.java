package db2.onlineshop.web.data;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseMapper<T, R> {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ModelMapper modelMapper = new ModelMapper();

    private final Class<R> type;

    public ResponseMapper(Class<R> type) {
        this.type = type;
    }

    public List<R> mapList(List<T> list) {
        List<R> result = list.stream()
                .map(o -> mapObject(o))
                .collect(Collectors.toList());
        log.trace("mapList:result.size={}", result.size());

        return result;
    }

    public R mapObject(T object) {
        R result = modelMapper.map(object, type);
        log.trace("mapObject:result={}", result);

        return result;
    }
}
