package com.indeed.iwf.condition;

public final class TimerCondition extends BaseCondition {

    public TimerCondition(int firingUnixTimestampSeconds) {
        super("");
        this.firingUnixTimestampSeconds = firingUnixTimestampSeconds;

    }

    // note: conditionId is needed when scheduling multiple timers in a state
    public TimerCondition(String conditionId, int firingUnixTimestampSeconds) {
        super(conditionId);
        this.firingUnixTimestampSeconds = firingUnixTimestampSeconds;
    }

    private int firingUnixTimestampSeconds;

    public int getFiringUnixTimestampSeconds() {
        return firingUnixTimestampSeconds;
    }
}