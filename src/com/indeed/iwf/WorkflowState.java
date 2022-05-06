package com.indeed.iwf;

import com.indeed.iwf.command.CommandRequest;
import com.indeed.iwf.command.CommandResults;

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
     * Implement this method to execute the commands set up for this state.
     *
     * @param input            the state input which is deserialized by dataConverter with {@link #getInputType}
     * @param queryAttributes  the query attributes that can be used as readOnly
     * @param searchAttributes the search attributes that can be used as readOnly
     * @return the requested commands for this step
     * NOTE: it's readonly here for simplifying the implementation(execute can be reverted in some edge cases),
     *       We could change to support R+W if necessary.  
     */
    CommandRequest execute(I input, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttributes);

    /**
     * Implement this method to decide what to do next when requested commands are ready
     *
     * @param input            the state input which is deserialized by dataConverter with {@link #getInputType}
     * @param commandResults   the results of the command that executed by {@link #execute}
     * @param queryAttributes  the query attributes that can be used as Read+Write
     * @param searchAttributes the search attributes that can be used as Read+Write
     * @return the decision of what to do next(e.g. transition to next states)
     */
    WorkflowStateDecision decide(final I input, final CommandResults commandResults,
                                 final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes);
}


