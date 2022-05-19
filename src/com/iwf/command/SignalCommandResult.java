package com.iwf.command;

public final class SignalCommandResult {
    private final String commandId;
    private final String signalName;
    private final Object signalValue;

    public SignalCommandResult(final String commandId, final String signalName, final Object signalValue) {
        this.commandId = commandId;
        this.signalName = signalName;
        this.signalValue = signalValue;
    }

    public String getCommandId() {
        return commandId;
    }

    public String getSignalName() {
        return signalName;
    }

    public Object getSignalValue() {
        return signalValue;
    }
}