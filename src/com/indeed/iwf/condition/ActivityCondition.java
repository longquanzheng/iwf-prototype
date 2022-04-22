package com.indeed.iwf.condition;

public final class ActivityCondition<O> extends BaseCondition {

    // below are fields supplied by user code via constructor
    private String activityType;
    private Object[] input;
    private Class<O> outputType;
    private ActivityOptions activityOptions;

    // below are fields managed by SDK/workflow engine
    // the values are provided when the activity are closed
    private O output;
    private ActivityStatus activityStatus;
    private ActivityTimeoutType activityTimeoutType;

    public ActivityCondition(final String activityType, final Class<O> outputType, ActivityOptions options, final Object... input) {
        super(options.getActivityConditionId());
        this.activityType = activityType;
        this.input = input;
        this.outputType = outputType;
        this.activityOptions = options;
    }

    String getActivityType() {
        return activityType;
    }

    Class<O> getOutputType() {
        return outputType;
    }

    ActivityOptions getActivityOptions() {
        return activityOptions;
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