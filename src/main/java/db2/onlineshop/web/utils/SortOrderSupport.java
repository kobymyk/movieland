package db2.onlineshop.web.utils;

import db2.onlineshop.entity.SortOrder;
import db2.onlineshop.web.exception.InvalidParamException;

import java.beans.PropertyEditorSupport;

public class SortOrderSupport extends PropertyEditorSupport {

    @Override
    public void setAsText(final String text) {
        if (text == null) { return; }

        try {
            SortOrder direction = SortOrder.getEnum(text);
            setValue(direction);
        } catch (IllegalArgumentException e) {
            throw new InvalidParamException("Invalid sorting param");
        }
    }
}
