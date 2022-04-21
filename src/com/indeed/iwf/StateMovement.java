package com.indeed.iwf;

public class StateMovement {
    private final String stateId;
    private final Object nextStateInput;

    /**
     * @param stateId        the stateId of the next state
     * @param nextStateInput the input of next state.
     *                       This must match the input type of next state otherwise a runtime exception will be thrown
     */
    public StateMovement(final String stateId, final Object nextStateInput) {
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
