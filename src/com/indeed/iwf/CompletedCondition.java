package com.indeed.iwf;

abstract class CompletedCondition {

    public CompletedCondition(String conditionID, final RequestedCondition requestedCondition) {
        this.conditionID = conditionID;
        this.requestedCondition = requestedCondition;
    }

    private String conditionID;
    private RequestedCondition requestedCondition;
    private Boolean completed;

    public String getConditionID() {
        return conditionID;
    }

    void setCompleted(boolean succ) {
        completed = succ;
    }

    public boolean isCompleted() {
        return completed;
    }

    public RequestedCondition getRequestedCondition() {
        return requestedCondition;
    }
}





