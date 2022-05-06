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

import static com.iwf.StateMovement.COMPLETING_WORKFLOW;

class SubscriptionOverState implements WorkflowState<Void> {

    @Override
    public String getStateId() {
        return SubscriptionWorkflow.WF_STATE_SUBSCRIPTION_OVER;
    }

    @Override
    public Class<Void> getInputType() {
        return Void.class;
    }

    @Override
    public CommandRequest execute(final Void nothing, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttributes) {
        final Customer customer = queryAttributes.get(SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER);
        return CommandRequest.forAnyCommandCompleted(
                new ActivityCommand<>("SubscriptionActivities::sendSubscriptionOverEmail", Void.class, new ActivityOptions(30), customer)
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