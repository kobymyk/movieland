package db2.onlineshop.dao.jdbc.builder;

import db2.onlineshop.entity.SortParam;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    private String sql;
    private StringBuilder builder;
    private List<SortParam> sortParams;
    private boolean wrapped;

    public QueryBuilder(String sql) {
        sortParams = new ArrayList<>();
        builder = new StringBuilder();
        // required
        this.sql = sql;
    }

    public QueryBuilder wrap() {
        wrapped = true;

        return this;
    }

    public QueryBuilder addSort(SortParam param) {
        sortParams.add(param);

        return this;
    }

    public String build() {
        if (wrapped) {
            builder.append("SELECT * FROM ( ").append(sql).append(" )");
        } else {
            builder.append(sql);
        }
        if (sortParams.size() > 0) {
            builder.append(" ORDER BY ");
            for (SortParam param : sortParams) {
                builder.append(param.getField()).append(" ")
                        .append(param.getDirection());
            }
        }

        return new String(builder.toString());
    }
}
