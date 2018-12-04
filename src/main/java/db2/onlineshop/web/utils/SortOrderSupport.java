package db2.onlineshop.web.utils;

import db2.onlineshop.entity.SortOrder;
import db2.onlineshop.web.exception.InvalidParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;

public class SortOrderSupport extends PropertyEditorSupport {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void setAsText(final String text) {
        if (text == null) { return; }

        try {
            SortOrder direction = SortOrder.getEnum(text);
            setValue(direction);
        } catch (IllegalArgumentException e) {
            log.error("setAsText:e={}", e);
            throw new InvalidParamException("Invalid sorting param", e);
        }
    }
}
