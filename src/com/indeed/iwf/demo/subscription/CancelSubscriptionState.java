package com.indeed.iwf.demo.subscription;

import com.indeed.iwf.StateDecision;
import com.indeed.iwf.WorkflowState;
import com.indeed.iwf.attributes.QueryAttributesRO;
import com.indeed.iwf.attributes.QueryAttributesRW;
import com.indeed.iwf.attributes.SearchAttributesRO;
import com.indeed.iwf.attributes.SearchAttributesRW;
import com.indeed.iwf.command.CommandRequest;
import com.indeed.iwf.command.CommandResults;
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
    public StateDecision decide(final Void nothing, final CommandResults commandResults,
                                final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        return new StateDecision(
                COMPLETING_WORKFLOW
        );
    }
}