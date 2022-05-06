package com.iwf;

/**
 * A holder class for {@link WorkflowState} and it's metadata (is startable)
 */
public final class StateDef {
    private final WorkflowState workflowState;
    private final boolean startable;

    /**
     * @param workflowState the state
     * @param startable     this indicates whether this state can be used to start the workflow
     */
    public StateDef(final WorkflowState workflowState, final boolean startable) {
        this.workflowState = workflowState;
        this.startable = startable;
    }

    public WorkflowState getWorkflowState() {
        return workflowState;
    }

    public boolean isStartable() {
        return startable;
    }
}
