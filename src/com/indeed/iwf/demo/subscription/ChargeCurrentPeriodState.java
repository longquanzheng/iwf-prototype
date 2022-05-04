package com.indeed.iwf.demo.subscription;

import com.indeed.iwf.QueryAttributesRO;
import com.indeed.iwf.QueryAttributesRW;
import com.indeed.iwf.SearchAttributesRO;
import com.indeed.iwf.SearchAttributesRW;
import com.indeed.iwf.StateMovement;
import com.indeed.iwf.WorkflowState;
import com.indeed.iwf.WorkflowStateDecision;
import com.indeed.iwf.condition.ActivityCondition;
import com.indeed.iwf.condition.ActivityOptions;
import com.indeed.iwf.condition.Prep;
import com.indeed.iwf.condition.SignalCondition;
import com.indeed.iwf.condition.TimerCondition;
import com.indeed.iwf.demo.subscription.models.Customer;

import java.util.List;

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
    public Prep prepare(final Void nothing, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttribute) {
        final Customer customer = searchAttributes.get(QUERY_ATTRIBUTE_CUSTOMER);
        final int currentPeriod = queryAttribute.get(QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER);
        return Prep.prepareAnyConditionCompleted(
                new ActivityCondition<>("SubscriptionActivities::chargeCustomerForBillingPeriod", Void.class, new ActivityOptions(30), customer, currentPeriod)
        );
    }

    @Override
    public WorkflowStateDecision decide(final Void nothing, final List<ActivityCondition<?>> activityConditions, final List<TimerCondition> timerConditions,
                                        final List<SignalCondition> signalConditions,
                                        final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        return new WorkflowStateDecision(
                new StateMovement(WF_STATE_WAIT_FOR_NEXT_PERIOD)
        );
    }
}
