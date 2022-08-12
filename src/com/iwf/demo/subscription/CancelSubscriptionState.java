package com.iwf.demo.subscription;

import com.iwf.StateDecision;
import com.iwf.WorkflowState;
import com.iwf.attributes.QueryAttributesRO;
import com.iwf.attributes.QueryAttributesRW;
import com.iwf.attributes.SearchAttributesRO;
import com.iwf.attributes.SearchAttributesRW;
import com.iwf.command.CommandRequest;
import com.iwf.command.CommandResults;
import com.iwf.command.SignalCommand;

import static com.iwf.StateDecision.COMPLETING_WORKFLOW;

public class CancelSubscriptionState implements WorkflowState<Void> {
    public static final String WF_STATE_CANCEL_SUBSCRIPTION = "cancelSubscription";

    @Override
    public String getStateId() {
        return WF_STATE_CANCEL_SUBSCRIPTION;
    }

    @Override
    public Class<Void> getInputType() {
        return Void.class;
    }

    @Override
    public CommandRequest start(final Void nothing, final SearchAttributesRW searchAttributes, final SearchAttributesRW queryAttributes) {
        return CommandRequest.forAllCommandCompleted(
                new SignalCommand(SubscriptionWorkflow.SIGNAL_METHOD_CANCEL_SUBSCRIPTION)
        );
    }

    @Override
    public StateDecision decide(final Void nothing, final CommandResults commandResults,
                                final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        return COMPLETING_WORKFLOW;
    }
}