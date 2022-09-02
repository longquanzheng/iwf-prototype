package com.iwf.demo.subscription;

import com.iwf.Context;
import com.iwf.StateDecision;
import com.iwf.StateMovement;
import com.iwf.WorkflowState;
import com.iwf.attributes.QueryAttributesRO;
import com.iwf.attributes.QueryAttributesRW;
import com.iwf.attributes.SearchAttributesRO;
import com.iwf.attributes.SearchAttributesRW;
import com.iwf.attributes.StateLocalAttributesR;
import com.iwf.attributes.StateLocalAttributesW;
import com.iwf.command.CommandRequest;
import com.iwf.command.CommandResults;
import com.iwf.command.SignalCommand;
import com.iwf.demo.subscription.models.Customer;

class UpdateChargeAmountState implements WorkflowState<Void> {

    public static final String WF_STATE_UPDATE_CHARGE_AMOUNT = "updateChargeAmount";

    @Override
    public String getStateId() {
        return WF_STATE_UPDATE_CHARGE_AMOUNT;
    }

    @Override
    public Class<Void> getInputType() {
        return Void.class;
    }

    @Override
    public CommandRequest start(final Context context, final Void nothing, final StateLocalAttributesW stateLocals, final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttribute) {
        return CommandRequest.forAllCommandCompleted(
                new SignalCommand(SubscriptionWorkflow.SIGNAL_METHOD_UPDATE_BILLING_PERIOD_CHARGE_AMOUNT)
        );
    }

    @Override
    public StateDecision decide(final Context context,final Void nothing, final CommandResults commandResults,final StateLocalAttributesR stateLocals,
                                final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {

        final int newAmount = commandResults.getSignalValueByIndex(0);
        final Customer customer = queryAttributes.get(SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER);
        customer.getSubscription().setBillingPeriodCharge(newAmount);
        queryAttributes.upsert(SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER, customer);

        return new StateDecision(
                new StateMovement(WF_STATE_UPDATE_CHARGE_AMOUNT) // go to a loop to update the value
        );
    }
}