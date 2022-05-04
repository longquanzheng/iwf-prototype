package com.indeed.iwf;

import com.indeed.iwf.condition.ActivityCondition;
import com.indeed.iwf.condition.Prep;
import com.indeed.iwf.condition.SignalCondition;
import com.indeed.iwf.condition.TimerCondition;

import java.util.List;

public interface WorkflowState<I> {
    /**
     * a unique identifier of the state
     */
    String getStateId();

    /**
     * this decides whether to load all the search attributes into {@link #decide} method
     * default to true
     */
    default boolean loadAllSearchAttributes() {
        return true;
    }

    /**
     * this decides whether to load all the query attributes into {@link #decide} method
     * default to true
     */
    default boolean loadAllQueryAttributes() {
        return true;
    }

    /**
     * This input type is needed for deserialize encoded data into Java object
     */
    Class<I> getInputType();

    /**
     * Implement this method to decide the conditions set up for this state.
     *
     * @param input            the state input which is deserialized by dataConverter with {@link #getInputType}
     * @param queryAttributes  TODO we probably can remove these two and calculate the upsertion based on input param so it would be SearchAttributesRO QueryAttributesRO (readonly)
     * @param searchAttributes
     * @return the requested conditions for this step
     */
    Prep prepare(I input, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttributes);

    /**
     * Implement this method to decide what to do next when any of the requested condition is ready
     * Note that this method will be executed on ANY condition is ready rather than waiting for all of them.
     * TODO similar as above, this can be QueryAttributesRW and SearchAttributesRW (read+write)
     * also we should have better abstraction of activityConditions/timerConditions/signalConditions for better strongly typing experience
     */
    WorkflowStateDecision decide(I input,
                                 List<ActivityCondition<?>> activityConditions,
                                 List<TimerCondition> timerConditions,
                                 List<SignalCondition> signalConditions,
                                 final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes);
}


