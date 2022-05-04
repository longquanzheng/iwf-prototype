package com.indeed.iwf.condition;

import java.util.List;

/**
 * This is the container of all requested conditions' results/statuses
 */
public class ConditionResults {

    public List<ActivityCondition> getAllActivityConditions() {
        return null;
    }

    public List<SignalCondition> getAllSignalConditions() {
        return null;
    }

    public List<TimerCondition> getAllTimerConditions() {
        return null;
    }

    public <T> T getActivityOutputByIndex(int idx) {
        return null;
    }

    public <T> T getActivityOutputById(String conditionId) {
        return null;
    }

    public ActivityCondition getActivityConditionByIndex(int idx) {
        return null;
    }

    public ActivityCondition getActivityConditionById(String conditionId) {
        return null;
    }

    public <T> T getSignalValueByIndex(int idx) {
        return null;
    }

    public <T> T getSignalValueById(String conditionId) {
        return null;
    }

}
