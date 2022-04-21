package com.indeed.iwf.condition;

public final class SignalCondition<T> extends RequestedCondition {

    public SignalCondition(String conditionId, String signalName, Class<T> signalType) {
        super(conditionId);
        this.signalName = signalName;
        this.signalType = signalType;
    }

    private String signalName;
    private Class<T> signalType;
    private T signal;

    public String getSignalName() {
        return signalName;
    }

    public Class<T> getSignalType() {
        return signalType;
    }

    void setSignal(T sig) {
        this.signal = sig;
    }

    public T getSignal() {
        return this.signal;
    }
}