package com.iwf.command;

public final class SignalCommand extends BaseCommand {

    public SignalCommand(final String signalName) {
        super("");
        this.signalName = signalName;
    }

    public SignalCommand(final String conditionId, final String signalName) {
        super(conditionId);
        this.signalName = signalName;
    }

    private final String signalName;
    private Object signalValue;

    public String getSignalName() {
        return signalName;
    }

    void setSignalValue(Object sig) {
        this.signalValue = sig;
    }

    public Object getSignalValue() {
        return this.signalValue;
    }
}