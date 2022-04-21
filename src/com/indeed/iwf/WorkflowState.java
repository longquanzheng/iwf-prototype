package com.indeed.iwf;

import com.indeed.iwf.condition.ActivityCondition;
import com.indeed.iwf.condition.BaseCondition;
import com.indeed.iwf.condition.SignalCondition;
import com.indeed.iwf.condition.TimerCondition;

import java.util.List;

public abstract class WorkflowState<I> {
    /**
     * @param stateId a unique identifier of the state
     */
    public WorkflowState(String stateId) {
        this.stateId = stateId;
    }

    private final String stateId;

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
    abstract List<BaseCondition> requestConditions(I input);

    /**
     * Implement this method to decide what to do next when any of the requested condition is ready
     * Note that this method will be executed on ANY condition is ready rather than waiting for all of them.
     */
    abstract WorkflowStateDecision decideNextStates(List<ActivityCondition<?>> activityConditions,
                                                    List<TimerCondition> timerConditions,
                                                    List<SignalCondition<?>> signalConditions);

    public String getStateId() {
        return stateId;
    }
}


