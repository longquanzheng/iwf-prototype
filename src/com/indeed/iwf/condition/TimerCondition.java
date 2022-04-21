package com.indeed.iwf.condition;

public final class TimerCondition extends BaseCondition {

    public TimerCondition(String conditionId, int firingUnixTimestampSeconds) {
        super(conditionId);
        this.firingUnixTimestampSeconds = firingUnixTimestampSeconds;
    }

    private int firingUnixTimestampSeconds;

    public int getFiringUnixTimestampSeconds() {
        return firingUnixTimestampSeconds;
    }
}