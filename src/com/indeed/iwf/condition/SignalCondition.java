package com.indeed.iwf.condition;

public final class SignalCondition<T> extends BaseCondition {

    public SignalCondition(final String conditionId, final String signalName, final Class<T> signalType) {
        super(conditionId);
        this.signalName = signalName;
        this.signalType = signalType;
    }

    private final String signalName;
    private final Class<T> signalType;
    private T signalValue;

    public String getSignalName() {
        return signalName;
    }

    public Class<T> getSignalType() {
        return signalType;
    }

    void setSignalValue(T sig) {
        this.signalValue = sig;
    }

    public T getSignalValue() {
        return this.signalValue;
    }
}