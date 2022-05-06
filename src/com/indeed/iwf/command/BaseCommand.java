package com.indeed.iwf.command;

public class BaseCommand {

    public BaseCommand(final String conditionId) {
        this.conditionId = conditionId;
    }

    private final String conditionId;

    public String getConditionId() {
        return conditionId;
    }
}





