package com.indeed.iwf;

import java.util.List;
import java.util.Map;

public class WorkflowStateDecision {

    /**
     * when using this constructor, it means the completed conditions are enough so state is completing
     *
     * @param nextStates             the next states that the workflow will be moved to
     * @param upsertSearchAttributes the search attributes that will be upsert
     */
    public WorkflowStateDecision(final List<StateMovement> nextStates, final Map<String, Object> upsertSearchAttributes) {
        this.nextStates = nextStates;
        this.upsertSearchAttributes = upsertSearchAttributes;
        waitForMoreCompletedConditions = false;
    }

    /**
     * when using this method, it means conditions are not enough so workflow will wait for more conditions to complete
     *
     * @return
     */
    public static WorkflowStateDecision waitForMoreCompletedConditions() {
        return new WorkflowStateDecision(true);
    }

    private WorkflowStateDecision(boolean waitForMoreCompletedConditions) {
        this.waitForMoreCompletedConditions = true;
        nextStates = null;
        upsertSearchAttributes = null;
    }

    public List<StateMovement> getNextStates() {
        return nextStates;
    }

    public Map<String, Object> getUpsertSearchAttributes() {
        return upsertSearchAttributes;
    }

    private final boolean waitForMoreCompletedConditions;
    private final List<StateMovement> nextStates;
    private final Map<String, Object> upsertSearchAttributes;

    // private final Map<String, Object> upsertMemo; TODO https://github.com/uber/cadence/issues/3729

}
