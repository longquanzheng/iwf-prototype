package com.indeed.iwf.condition;

public class ActivityOptions {

    // TODO: other optional configs: 1. retryOption, 2. tasklist 3, other detailed timeouts(e.g. startToClose, heartbeat)

    private String activityConditionId; // optional, needed when scheduled multiple activities with same type in a state
    private int startToCloseTimeoutSeconds;

    public ActivityOptions(final int startToCloseTimeoutSeconds) {
        this.activityConditionId = "";
        this.startToCloseTimeoutSeconds = startToCloseTimeoutSeconds;
    }

    public ActivityOptions(final String activityId, final int startToCloseTimeoutSeconds) {
        this.activityConditionId = activityId;
        this.startToCloseTimeoutSeconds = startToCloseTimeoutSeconds;
    }

    public int getStartToCloseTimeoutSeconds() {
        return startToCloseTimeoutSeconds;
    }

    public void setStartToCloseTimeoutSeconds(final int startToCloseTimeoutSeconds) {
        this.startToCloseTimeoutSeconds = startToCloseTimeoutSeconds;
    }

    public String getActivityConditionId() {
        return activityConditionId;
    }

    public void setActivityConditionId(final String activityConditionId) {
        this.activityConditionId = activityConditionId;
    }
}
