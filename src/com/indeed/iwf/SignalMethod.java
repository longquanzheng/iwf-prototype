package com.indeed.iwf;

public final class SignalMethod {
    private String signalName;
    private Class signalType;

    public SignalMethod(final String signalName, final Class signalType) {
        this.signalName = signalName;
        this.signalType = signalType;
    }

    public Class getSignalType() {
        return signalType;
    }

    public String getSignalName() {
        return signalName;
    }
}
