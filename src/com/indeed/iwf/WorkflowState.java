package com.indeed.iwf;

import com.indeed.iwf.condition.CompletedCondition;
import com.indeed.iwf.condition.RequestedCondition;

import java.util.List;

public abstract class WorkflowState<I> {

    // Below are preserved StateIds
    String STARTED_STATE_ID = "__Started__";
    String COMPLETED_STATE_ID = "__Completed__";
    String FAILED_STATE_ID = "__Failed__";
    String TERMINATED_STATE_ID = "__Terminated__";
    String CANCELED_STATE_ID = "__Canceled__";

    /**
     * @param stateId a unique identifier of the state
     */
    public WorkflowState(String stateId) {
        this.stateId = stateId;
    }

    private final String stateId;

    /**
     * Implement this method to cast an object from previous state into the input of this state
     *
     * @param untypedInput   the object of the input without knowing the type
     * @param previousStateId the stateId of the previous state
     * @return cast the input into the right type
     */
    abstract I castInput(Object untypedInput, String previousStateId);

    /**
     * Implement this method to decide the conditions set up for this state.
     *
     * @param input the state input which is returned by {@link #castInput}
     * @return the requested conditions for this step
     */
    abstract List<RequestedCondition> requestConditions(I input);

    /**
     * Implement this method to decide what to do next when any of the requested condition is completed
     * Note that this method will be executed on every condition completion rather than waiting for all of them.
     *
     * @param completedConditions all completed conditions that requested in {@link #requestConditions}
     * @return
     */
    abstract DecidingResult decideNextStates(List<CompletedCondition> completedConditions);

    public String getStateId() {
        return stateId;
    }
}


