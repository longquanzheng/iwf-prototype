package com.indeed.iwf.condition;

public final class ActivityCondition<O> extends RequestedCondition {

    public ActivityCondition(String conditionId, String activityType, Object input, Class<O> outputType, int timeoutInSeconds) {
        super(conditionId);
        this.activityType = activityType;
        this.input = input;
        this.outputType = outputType;
        this.timeoutInSeconds = timeoutInSeconds;
    }

    private String activityType;
    private Object input;
    private Class<O> outputType;
    private O output;
    private int timeoutInSeconds;

    // TODO: other optional configs: 1. retryOption, 2. tasklist 3, other detailed timeouts(e.g. startToClose, heartbeat)

    public String getActivityType() {
        return activityType;
    }

    public Class<O> getOutputType() {
        return outputType;
    }

    void setOutput(O out) {
        this.output = out;
    }

    public O getOutput() {
        return output;
    }

    public int getTimeoutInSeconds() {
        return timeoutInSeconds;
    }

    public Object getInput() {
        return input;
    }
}