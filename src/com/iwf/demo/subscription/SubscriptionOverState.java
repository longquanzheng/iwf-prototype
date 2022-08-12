package com.iwf.demo.subscription;

import com.iwf.StateDecision;
import com.iwf.WorkflowState;
import com.iwf.attributes.QueryAttributesRO;
import com.iwf.attributes.QueryAttributesRW;
import com.iwf.attributes.SearchAttributesRO;
import com.iwf.attributes.SearchAttributesRW;
import com.iwf.command.ActivityCommand;
import com.iwf.command.ActivityOptions;
import com.iwf.command.CommandRequest;
import com.iwf.command.CommandResults;
import com.iwf.demo.subscription.models.Customer;

import static com.iwf.demo.subscription.SubscriptionWorkflow.SEND_SUBSCRIPTION_OVER_EMAIL_ACTIVITY;

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
    public CommandRequest start(final Void nothing, final SearchAttributesRW searchAttributes, final SearchAttributesRW queryAttributes) {
        final Customer customer = queryAttributes.get(SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER);
        return CommandRequest.forAllCommandCompleted(
                new ActivityCommand(SEND_SUBSCRIPTION_OVER_EMAIL_ACTIVITY, new ActivityOptions(30), customer)
        );
    }

    @Override
    public StateDecision decide(final Void nothing, final CommandResults commandResults,
                                final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        return StateDecision.COMPLETING_WORKFLOW;
    }
}