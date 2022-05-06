package com.indeed.iwf;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WorkflowStateDecision {
    private final boolean needMoreReadyCommands;
    private final List<StateMovement> nextStates;
    private final Map<String, Object> upsertSearchAttributes;
    private final Map<String, Object> upsertQueryAttributes;

    /**
     * when using this constructor, it means the completed commands are enough so state is completing
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
        needMoreReadyCommands = false;
    }

    /**
     * when using this method, it means commands are not enough so workflow will wait for more commands to complete
     *
     * @return
     */
    public static WorkflowStateDecision WaitForMoreCommands() {
        return new WorkflowStateDecision(true);
    }

    private WorkflowStateDecision(boolean needMoreReadyCommands) {
        this.needMoreReadyCommands = true;
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

    public boolean needMoreReadyCommands() {
        return needMoreReadyCommands;
    }
}
