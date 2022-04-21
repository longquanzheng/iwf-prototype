package com.indeed.iwf.condition;

public abstract class RequestedCondition {

    public RequestedCondition(String conditionId) {
        this.conditionId = conditionId;
    }

    private String conditionId;
    private boolean completed;

    public String getConditionId() {
        return conditionId;
    }

    void setCompleted(boolean succ) {
        completed = succ;
    }

    public boolean isCompleted() {
        return completed;
    }
}





