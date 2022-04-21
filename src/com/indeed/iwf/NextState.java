package com.indeed.iwf;

public class NextState {
    private String stateId;
    private Object nextStateInput;

    public NextState(final String stateId, final Object nextStateInput) {
        this.stateId = stateId;
        this.nextStateInput = nextStateInput;
    }

    public String getStateId() {
        return stateId;
    }

    public Object getNextStateInput() {
        return nextStateInput;
    }
}
