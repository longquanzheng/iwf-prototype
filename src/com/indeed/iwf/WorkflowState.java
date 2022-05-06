package com.indeed.iwf;

import com.indeed.iwf.command.CommandRequest;
import com.indeed.iwf.command.ConditionResults;

public interface WorkflowState<I> {
    /**
     * a unique identifier of the state
     */
    String getStateId();

    /**
     * this decides whether to load all the search attributes into {@link #decide} and {@link #execute} method
     * default to true
     */
    default boolean loadAllSearchAttributes() {
        return true;
    }

    /**
     * this decides whether to load all the query attributes into {@link #decide} and {@link #execute} method
     * default to true
     */
    default boolean loadAllQueryAttributes() {
        return true;
    }

    /**
     * This input type is needed for deserializing data back into Java object
     */
    Class<I> getInputType();

    /**
     * Implement this method to decide the conditions set up for this state.
     *
     * @param input            the state input which is deserialized by dataConverter with {@link #getInputType}
     * @param queryAttributes
     * @param searchAttributes
     * @return the requested conditions for this step
     */
    CommandRequest execute(I input, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttributes);

    /**
     * Implement this method to decide what to do next when requested conditions are ready
     */
    WorkflowStateDecision decide(final I input, final ConditionResults conditionResults,
                                 final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes);
}


