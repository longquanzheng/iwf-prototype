package com.indeed.iwf.demo.subscription;

import com.indeed.iwf.QueryAttributesRO;
import com.indeed.iwf.QueryAttributesRW;
import com.indeed.iwf.SearchAttributesRO;
import com.indeed.iwf.SearchAttributesRW;
import com.indeed.iwf.StateMovement;
import com.indeed.iwf.WorkflowState;
import com.indeed.iwf.WorkflowStateDecision;
import com.indeed.iwf.condition.ActivityCondition;
import com.indeed.iwf.condition.Prep;
import com.indeed.iwf.condition.SignalCondition;
import com.indeed.iwf.condition.TimerCondition;
import com.indeed.iwf.demo.subscription.models.Customer;

import java.util.List;

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
    public Prep prepare(final Void nothing, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttribute) {
        return Prep.prepareAnyConditionCompleted(
                new SignalCondition(SubscriptionWorkflow.SIGNAL_METHOD_UPDATE_BILLING_PERIOD_CHARGE_AMOUNT)
        );
    }

    @Override
    public WorkflowStateDecision decide(final Void nothing, final List<ActivityCondition<?>> activityConditions,
                                        final List<TimerCondition> timerConditions, final List<SignalCondition> signalConditions,
                                        final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {

        final int newAmount = (int) signalConditions.get(0).getSignalValue();
        final Customer customer = queryAttributes.get(QUERY_ATTRIBUTE_CUSTOMER);
        customer.getSubscription().setBillingPeriodCharge(newAmount);
        queryAttributes.upsert(QUERY_ATTRIBUTE_CUSTOMER, customer);

        return new WorkflowStateDecision(
                new StateMovement(WF_STATE_UPDATE_CHARGE_AMOUNT) // go to a loop to update the value
        );
    }
}