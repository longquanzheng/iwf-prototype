package com.indeed.iwf;

public final class TimerCondition extends RequestedCondition {

    public TimerCondition(String conditionID, int timerInSeconds) {
        super(conditionID);
        this.timerInSeconds = timerInSeconds;
    }

    private int timerInSeconds;

    public int getTimerInSeconds() {
        return timerInSeconds;
    }
}