package com.indeed.iwf.condition;

public abstract class CompletedCondition {

    public CompletedCondition(String conditionId, final RequestedCondition requestedCondition) {
        this.conditionId = conditionId;
        this.requestedCondition = requestedCondition;
    }

    private String conditionId;
    private RequestedCondition requestedCondition;
    private Boolean completed;

    public String getConditionId() {
        return conditionId;
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





