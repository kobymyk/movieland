package db2.onlineshop.web.utils;

import db2.onlineshop.entity.SortOrder;
import db2.onlineshop.web.exception.InvalidParamException;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SortOrderSupportTest {
    private SortOrderSupport support = new SortOrderSupport();

    @Test
    public void setAsText() {
        setText(null);
        assertNull(getSortOrder());

        setText("asc");
        assertEquals(SortOrder.ASC, getSortOrder());

        setText("desc");
        assertEquals(SortOrder.DESC, getSortOrder());
    }

    @Test(expected = InvalidParamException.class)
    @Ignore
    public void setAsTextException() {
        setText("xxx");
    }

    private SortOrder getSortOrder() {
        return (SortOrder) support.getValue();
    }

    private void setText(String text) {
        support.setAsText(text);
    }
}