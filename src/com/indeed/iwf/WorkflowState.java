package com.indeed.iwf;

import java.util.List;

public abstract class WorkflowState<I, O> {
    String STARTED_STATE_ID = "__Started__";
    String COMPLETED_STATE_ID = "__Completed__";
    String FAILED_STATE_ID = "__Failed__";
    String TERMINATED_STATE_ID = "__Terminated__";
    String CANCELED_STATE_ID = "__Canceled__";

    /**
     * @param stateID    a unique identifier of the state
     * @param outputType the output type of the state so that SDK can decode the data using dataConverter
     */
    public WorkflowState(String stateID, Class<O> outputType) {
        this.stateID = stateID;
        this.outputType = outputType;
    }

    private final String stateID;
    private final Class<O> outputType;

    /**
     * Implement this method to convert a source input(an output of a previous state, or workflow input) into the input of the state
     *
     * @param sourceInput   the object of the source input(an output of a previous state, or workflow input)
     * @param sourceStateID the stateID of the previous state
     * @return the converted result
     */
    abstract I convertToInputType(Object sourceInput, String sourceStateID);

    /**
     * Implement this method to decide the conditions set up for this state.
     *
     * @param input the state input which is returned by {@link #convertToInputType}
     * @return the requested conditions for this step
     */
    abstract List<RequestedCondition> prepareDecidingConditions(I input);

    /**
     * Implement this method to decide what to do next when any of the requested condition is completed
     * Note that this method will be executed on every condition completion rather than waiting for all of them.
     *
     * @param completedConditions all completed conditions that requested in {@link #prepareDecidingConditions}
     * @return
     */
    abstract DecidingResult<O> decideNextStates(List<CompletedCondition> completedConditions);

    public String getStateID() {
        return stateID;
    }

    public Class<O> getOutputType() {
        return outputType;
    }
}


