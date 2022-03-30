package com.indeed.iwf;

abstract class RequestedCondition {

    public RequestedCondition(String conditionID) {
        this.conditionID = conditionID;
    }

    private String conditionID;
    private boolean completed;

    public String getConditionID() {
        return conditionID;
    }

    void setCompleted(boolean succ) {
        completed = succ;
    }

    public boolean isCompleted() {
        return completed;
    }
}





