package com.iwf.command;

public final class ActivityDef<O> {
    private String activityType;
    private Class<O> outputType;

    public ActivityDef(final String activityType, final Class<O> outputType) {
        this.activityType = activityType;
        this.outputType = outputType;
    }

    public String getActivityType() {
        return activityType;
    }

    public Class<O> getOutputType() {
        return outputType;
    }
}
