package com.iwf.demo.subscription;

import com.iwf.StateDecision;
import com.iwf.StateMovement;
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

class ChargeCurrentPeriodState implements WorkflowState<Void> {

    @Override
    public String getStateId() {
        return SubscriptionWorkflow.WF_STATE_CHARGE_CURRENT_PERIOD;
    }

    @Override
    public Class<Void> getInputType() {
        return Void.class;
    }

    @Override
    public CommandRequest execute(final Void nothing, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttribute) {
        final Customer customer = searchAttributes.get(SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER);
        final int currentPeriod = queryAttribute.get(SubscriptionWorkflow.QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER);
        return CommandRequest.forAnyCommandCompleted(
                new ActivityCommand<>("SubscriptionActivities::chargeCustomerForBillingPeriod", Void.class, new ActivityOptions(30), customer, currentPeriod)
        );
    }

    @Override
    public StateDecision decide(final Void nothing, final CommandResults commandResults,
                                final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        return new StateDecision(
                new StateMovement(SubscriptionWorkflow.WF_STATE_WAIT_FOR_NEXT_PERIOD)
        );
    }
}
