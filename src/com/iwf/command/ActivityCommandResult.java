package com.iwf.command;

public final class ActivityCommandResult {

    private String activityType;
    private String activityCommandId;

    private Object output;
    private ActivityStatus activityStatus;
    private ActivityTimeoutType activityTimeoutType;

    public ActivityCommandResult(final String activityType, final String activityCommandId, final Object output, final ActivityStatus activityStatus, final ActivityTimeoutType activityTimeoutType) {
        this.activityType = activityType;
        this.activityCommandId = activityCommandId;
        this.output = output;
        this.activityStatus = activityStatus;
        this.activityTimeoutType = activityTimeoutType;
    }

    public String getActivityType() {
        return activityType;
    }

    public Object getOutput() {
        return output;
    }

    public ActivityTimeoutType getActivityTimeoutType() {
        return activityTimeoutType;
    }

    public ActivityStatus getActivityStatus() {
        return activityStatus;
    }

    public String getActivityCommandId() {
        return activityCommandId;
    }
}