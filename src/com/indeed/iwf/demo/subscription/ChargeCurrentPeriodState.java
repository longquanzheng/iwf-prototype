package com.indeed.iwf.demo.subscription;

import com.indeed.iwf.StateDecision;
import com.indeed.iwf.StateMovement;
import com.indeed.iwf.WorkflowState;
import com.indeed.iwf.attributes.QueryAttributesRO;
import com.indeed.iwf.attributes.QueryAttributesRW;
import com.indeed.iwf.attributes.SearchAttributesRO;
import com.indeed.iwf.attributes.SearchAttributesRW;
import com.indeed.iwf.command.ActivityCommand;
import com.indeed.iwf.command.ActivityOptions;
import com.indeed.iwf.command.CommandRequest;
import com.indeed.iwf.command.CommandResults;
import com.indeed.iwf.demo.subscription.models.Customer;

import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.WF_STATE_CHARGE_CURRENT_PERIOD;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.WF_STATE_WAIT_FOR_NEXT_PERIOD;

class ChargeCurrentPeriodState implements WorkflowState<Void> {

    @Override
    public String getStateId() {
        return WF_STATE_CHARGE_CURRENT_PERIOD;
    }

    @Override
    public Class<Void> getInputType() {
        return Void.class;
    }

    @Override
    public CommandRequest execute(final Void nothing, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttribute) {
        final Customer customer = searchAttributes.get(QUERY_ATTRIBUTE_CUSTOMER);
        final int currentPeriod = queryAttribute.get(QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER);
        return CommandRequest.forAnyCommandCompleted(
                new ActivityCommand<>("SubscriptionActivities::chargeCustomerForBillingPeriod", Void.class, new ActivityOptions(30), customer, currentPeriod)
        );
    }

    @Override
    public StateDecision decide(final Void nothing, final CommandResults commandResults,
                                final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        return new StateDecision(
                new StateMovement(WF_STATE_WAIT_FOR_NEXT_PERIOD)
        );
    }
}
