package com.indeed.iwf;

public final class QueryAttribute<T> {
    private String key;
    private Class<T> type;

    public QueryAttribute(final String key, final Class<T> type) {
        this.key = key;
        this.type = type;
    }

    public Class<T> getQueryAttributeType() {
        return type;
    }

    public String QueryAttributeKey() {
        return key;
    }
}
