package com.indeed.iwf.demo.subscription;

import com.indeed.iwf.QueryAttributesRO;
import com.indeed.iwf.QueryAttributesRW;
import com.indeed.iwf.SearchAttributesRO;
import com.indeed.iwf.SearchAttributesRW;
import com.indeed.iwf.WorkflowState;
import com.indeed.iwf.WorkflowStateDecision;
import com.indeed.iwf.command.CommandRequest;
import com.indeed.iwf.command.ConditionResults;
import com.indeed.iwf.command.SignalCommand;

import static com.indeed.iwf.StateMovement.COMPLETING_WORKFLOW;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.WF_STATE_CANCEL_SUBSCRIPTION;

public class CancelSubscriptionState implements WorkflowState<Void> {

    @Override
    public String getStateId() {
        return WF_STATE_CANCEL_SUBSCRIPTION;
    }

    @Override
    public Class<Void> getInputType() {
        return Void.class;
    }

    @Override
    public CommandRequest execute(final Void nothing, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttributes) {
        return CommandRequest.forAnyCommandCompleted(
                new SignalCommand(SubscriptionWorkflow.SIGNAL_METHOD_CANCEL_SUBSCRIPTION)
        );
    }

    @Override
    public WorkflowStateDecision decide(final Void nothing, final ConditionResults conditionResults,
                                        final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        return new WorkflowStateDecision(
                COMPLETING_WORKFLOW
        );
    }
}