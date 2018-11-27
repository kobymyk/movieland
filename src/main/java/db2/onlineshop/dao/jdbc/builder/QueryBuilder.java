package db2.onlineshop.dao.jdbc.builder;

import db2.onlineshop.entity.SortParam;

public class QueryBuilder {
    private String sql;

    public QueryBuilder(String sql) {
        this.sql = sql;
    }

    public QueryBuilder wrap() {
        sql = new StringBuilder()
                .append("SELECT * FROM ( ").append(sql).append(" )")
                .toString();

        return this;
    }

    public QueryBuilder sort(SortParam param) {
        sql = new StringBuilder()
                .append(sql).append(" ORDER BY ").append(param.getField()).append(" ")
                .append(param.getDirection())
                .toString();

        return this;
    }

    public String build() {
        return new String(sql);
    }
}
