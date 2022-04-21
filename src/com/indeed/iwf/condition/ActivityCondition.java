package com.indeed.iwf.condition;

public final class ActivityCondition<O> extends BaseCondition {

    // below are fields supplied by user code via constructor
    private String activityType;
    private Object input;
    private Class<O> outputType;
    private int timeoutInSeconds;

    // below are fields managed by SDK/workflow engine
    // the values are provided when the activity are closed
    private O output;
    private ActivityStatus activityStatus;
    private ActivityTimeoutType activityTimeoutType;

    // TODO: other optional configs: 1. retryOption, 2. tasklist 3, other detailed timeouts(e.g. startToClose, heartbeat)

    public ActivityCondition(final String conditionId, final String activityType, final Object input,
                             final Class<O> outputType, final int timeoutInSeconds) {
        super(conditionId);
        this.activityType = activityType;
        this.input = input;
        this.outputType = outputType;
        this.timeoutInSeconds = timeoutInSeconds;
    }

    String getActivityType() {
        return activityType;
    }

    Class<O> getOutputType() {
        return outputType;
    }

    int getTimeoutInSeconds() {
        return timeoutInSeconds;
    }

    Object getInput() {
        return input;
    }

    public O getOutput() {
        return output;
    }

    void setOutput(O out) {
        this.output = out;
    }

    public ActivityTimeoutType getActivityTimeoutType() {
        return activityTimeoutType;
    }

    void setActivityTimeoutType(final ActivityTimeoutType activityTimeoutType) {
        this.activityTimeoutType = activityTimeoutType;
    }

    public ActivityStatus getActivityStatus() {
        return activityStatus;
    }

    void setActivityStatus(final ActivityStatus activityStatus) {
        this.activityStatus = activityStatus;
    }
}