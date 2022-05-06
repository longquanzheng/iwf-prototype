package com.indeed.iwf.command;

import java.util.List;

/**
 * This is the container of all requested commands' results/statuses
 */
public class CommandResults {

    public List<ActivityCommand> getAllActivityCommands() {
        return null;
    }

    public List<SignalCommand> getAllSignalCommands() {
        return null;
    }

    public List<TimerCommand> getAllTimerCommands() {
        return null;
    }

    public <T> T getActivityOutputByIndex(int idx) {
        return null;
    }

    public <T> T getActivityOutputById(String commandId) {
        return null;
    }

    public ActivityCommand getActivityCommandByIndex(int idx) {
        return null;
    }

    public ActivityCommand getActivityCommandById(String commandId) {
        return null;
    }

    public <T> T getSignalValueByIndex(int idx) {
        return null;
    }

    public <T> T getSignalValueById(String commandId) {
        return null;
    }

}
