package com.indeed.iwf.demo.subscription;

import com.indeed.iwf.StateDecision;
import com.indeed.iwf.StateMovement;
import com.indeed.iwf.WorkflowState;
import com.indeed.iwf.attributes.QueryAttributesRO;
import com.indeed.iwf.attributes.QueryAttributesRW;
import com.indeed.iwf.attributes.SearchAttributesRO;
import com.indeed.iwf.attributes.SearchAttributesRW;
import com.indeed.iwf.command.CommandRequest;
import com.indeed.iwf.command.CommandResults;
import com.indeed.iwf.command.SignalCommand;
import com.indeed.iwf.demo.subscription.models.Customer;

import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.WF_STATE_UPDATE_CHARGE_AMOUNT;

class UpdateChargeAmountState implements WorkflowState<Void> {

    @Override
    public String getStateId() {
        return WF_STATE_UPDATE_CHARGE_AMOUNT;
    }

    @Override
    public Class<Void> getInputType() {
        return Void.class;
    }

    @Override
    public CommandRequest execute(final Void nothing, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttribute) {
        return CommandRequest.forAnyCommandCompleted(
                new SignalCommand(SubscriptionWorkflow.SIGNAL_METHOD_UPDATE_BILLING_PERIOD_CHARGE_AMOUNT)
        );
    }

    @Override
    public StateDecision decide(final Void nothing, final CommandResults commandResults,
                                final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {

        final int newAmount = commandResults.getSignalValueByIndex(0);
        final Customer customer = queryAttributes.get(QUERY_ATTRIBUTE_CUSTOMER);
        customer.getSubscription().setBillingPeriodCharge(newAmount);
        queryAttributes.upsert(QUERY_ATTRIBUTE_CUSTOMER, customer);

        return new StateDecision(
                new StateMovement(WF_STATE_UPDATE_CHARGE_AMOUNT) // go to a loop to update the value
        );
    }
}