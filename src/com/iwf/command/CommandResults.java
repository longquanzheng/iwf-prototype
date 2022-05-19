package com.iwf.command;

import java.util.List;

/**
 * This is the container of all requested commands' results/statuses
 */
public class CommandResults {

    public List<ActivityCommandResult> getAllActivityCommandResults() {
        return null;
    }

    public List<SignalCommandResult> getAllSignalCommandResults() {
        return null;
    }

    public List<TimerCommandResult> getAllTimerCommandResults() {
        return null;
    }

    public <T> T getActivityOutputByIndex(int idx) {
        return null;
    }

    public <T> T getActivityOutputById(String commandId) {
        return null;
    }

    public ActivityCommandResult getActivityCommandResultByIndex(int idx) {
        return null;
    }

    public ActivityCommandResult getActivityCommandResultById(String commandId) {
        return null;
    }

    public <T> T getSignalValueByIndex(int idx) {
        return null;
    }

    public <T> T getSignalValueById(String commandId) {
        return null;
    }

}
