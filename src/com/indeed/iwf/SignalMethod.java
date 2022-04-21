package com.indeed.iwf;

public final class SignalMethod<T> {
    private String signalName;
    private Class<T> signalType;

    public SignalMethod(final String signalName, final Class<T> signalType) {
        this.signalName = signalName;
        this.signalType = signalType;
    }

    public Class<T> getSignalType() {
        return signalType;
    }

    public String getSignalName() {
        return signalName;
    }
}
