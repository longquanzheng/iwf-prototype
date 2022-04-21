package com.indeed.iwf;

import com.indeed.iwf.condition.ActivityCondition;
import com.indeed.iwf.condition.BaseCondition;
import com.indeed.iwf.condition.SignalCondition;
import com.indeed.iwf.condition.TimerCondition;

import java.util.List;
import java.util.Map;

public abstract class WorkflowState<I> {
    /**
     * @param stateId a unique identifier of the state
     */
    public WorkflowState(String stateId) {
        this.stateId = stateId;
    }

    private final String stateId;

    /**
     * this decides whether to load all the search attributes into {@link #decide} method
     * default to true
     * TODO: allow override by constructor to save network bandwidth
     */
    private boolean loadAllSearchAttributes = true;
    /**
     * this decides whether to load all the query attributes into {@link #decide} method
     * default to true
     * TODO: allow override by constructor to save network bandwidth
     */
    private boolean loadAllQueryAttributes = true;

    /**
     * This input type is needed for deserialize encoded data into Java object
     */
    abstract Class<I> getInputType();

    /**
     * Implement this method to decide the conditions set up for this state.
     *
     * @param input the state input which is deserialized by dataConverter with {@link #getInputType}
     * @return the requested conditions for this step
     */
    abstract List<BaseCondition> prepare(I input);

    /**
     * Implement this method to decide what to do next when any of the requested condition is ready
     * Note that this method will be executed on ANY condition is ready rather than waiting for all of them.
     */
    abstract WorkflowStateDecision decide(List<ActivityCondition<?>> activityConditions,
                                          List<TimerCondition> timerConditions,
                                          List<SignalCondition<?>> signalConditions,
                                          Map<String, Object> searchAttributes,
                                          Map<String, Object> queryAttributes);

    public String getStateId() {
        return stateId;
    }
}


