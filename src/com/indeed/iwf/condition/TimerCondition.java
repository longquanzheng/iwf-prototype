package com.indeed.iwf.condition;

public final class TimerCondition extends RequestedCondition {

    public TimerCondition(String conditionId, int firingUnixTimestamp) {
        super(conditionId);
        this.firingUnixTimestamp = firingUnixTimestamp;
    }

    private int firingUnixTimestamp;

    public int getFiringUnixTimestamp() {
        return firingUnixTimestamp;
    }
}