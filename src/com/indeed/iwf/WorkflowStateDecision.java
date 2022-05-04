package com.indeed.iwf;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WorkflowStateDecision {
    private final boolean needMoreReadyConditions;
    private final List<StateMovement> nextStates;
    private final Map<String, Object> upsertSearchAttributes;
    private final Map<String, Object> upsertQueryAttributes;

    /**
     * when using this constructor, it means the completed conditions are enough so state is completing
     *
     * @param nextStates the next states that the workflow will be moved to
     */
    public WorkflowStateDecision(final StateMovement... nextStates) {
        this(Arrays.asList(nextStates));
    }

    public WorkflowStateDecision(final List<StateMovement> nextStates) {
        this.nextStates = nextStates;
        this.upsertSearchAttributes = null; // NOTE that this will be calculated based on operation of SearchAttributeRW
        this.upsertQueryAttributes = null; // NOTE that this will be calculated based on operation of QueryAttributeRW
        needMoreReadyConditions = false;
    }

    /**
     * when using this method, it means conditions are not enough so workflow will wait for more conditions to complete
     *
     * @return
     */
    public static WorkflowStateDecision WaitForMoreConditions() {
        return new WorkflowStateDecision(true);
    }

    private WorkflowStateDecision(boolean needMoreReadyConditions) {
        this.needMoreReadyConditions = true;
        nextStates = null;
        upsertSearchAttributes = null;
        upsertQueryAttributes = null;
    }

    public List<StateMovement> getNextStates() {
        return nextStates;
    }

    public Map<String, Object> getUpsertSearchAttributes() {
        return upsertSearchAttributes;
    }

    public Map<String, Object> getUpsertQueryAttributes() {
        return upsertQueryAttributes;
    }

    public boolean needMoreReadyConditions() {
        return needMoreReadyConditions;
    }
}
