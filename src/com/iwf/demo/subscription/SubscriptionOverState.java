package com.iwf.demo.subscription;

import com.iwf.StateDecision;
import com.iwf.WorkflowState;
import com.iwf.attributes.QueryAttributesRO;
import com.iwf.attributes.QueryAttributesRW;
import com.iwf.attributes.SearchAttributesRO;
import com.iwf.attributes.SearchAttributesRW;
import com.iwf.attributes.StateLocalAttributesR;
import com.iwf.attributes.StateLocalAttributesW;
import com.iwf.command.ActivityCommand;
import com.iwf.command.ActivityOptions;
import com.iwf.command.CommandRequest;
import com.iwf.command.CommandResults;
import com.iwf.demo.subscription.models.Customer;

class SubscriptionOverState implements WorkflowState<Void> {

    public static final String WF_STATE_SUBSCRIPTION_OVER = "subscriptionOver";

    @Override
    public String getStateId() {
        return WF_STATE_SUBSCRIPTION_OVER;
    }

    @Override
    public Class<Void> getInputType() {
        return Void.class;
    }

    @Override
    public CommandRequest start(final Void nothing, final StateLocalAttributesW stateLocals, final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        final Customer customer = queryAttributes.get(SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER);
        // invoke API here to send subscription over email.
        // control the timeout by customizing the WorkflowStateOptions
        return CommandRequest.none();
    }

    @Override
    public StateDecision decide(final Void nothing, final CommandResults commandResults,final StateLocalAttributesR stateLocals,
                                final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        return StateDecision.COMPLETING_WORKFLOW;
    }
}