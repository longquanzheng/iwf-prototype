package com.indeed.iwf.command;

import java.util.List;

/**
 * This is the container of all requested conditions' results/statuses
 */
public class CommandResults {

    public List<ActivityCommand> getAllActivityConditions() {
        return null;
    }

    public List<SignalCommand> getAllSignalConditions() {
        return null;
    }

    public List<TimerCommand> getAllTimerConditions() {
        return null;
    }

    public <T> T getActivityOutputByIndex(int idx) {
        return null;
    }

    public <T> T getActivityOutputById(String conditionId) {
        return null;
    }

    public ActivityCommand getActivityConditionByIndex(int idx) {
        return null;
    }

    public ActivityCommand getActivityConditionById(String conditionId) {
        return null;
    }

    public <T> T getSignalValueByIndex(int idx) {
        return null;
    }

    public <T> T getSignalValueById(String conditionId) {
        return null;
    }

}
