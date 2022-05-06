package com.indeed.iwf.command;

public final class TimerCommand extends BaseCommand {

    public TimerCommand(int firingUnixTimestampSeconds) {
        super("");
        this.firingUnixTimestampSeconds = firingUnixTimestampSeconds;

    }

    // note: conditionId is needed when scheduling multiple timers in a state
    public TimerCommand(String conditionId, int firingUnixTimestampSeconds) {
        super(conditionId);
        this.firingUnixTimestampSeconds = firingUnixTimestampSeconds;
    }

    private int firingUnixTimestampSeconds;

    public int getFiringUnixTimestampSeconds() {
        return firingUnixTimestampSeconds;
    }
}