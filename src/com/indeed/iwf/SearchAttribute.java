package com.indeed.iwf;

public final class SearchAttribute<T> {
    private String key;
    private Class<T> type;

    public SearchAttribute(final String key, final Class<T> type) {
        this.key = key;
        this.type = type;
    }

    public Class<T> getSearchAttributeType() {
        return type;
    }

    public String getSearchAttributeKey() {
        return key;
    }
}
