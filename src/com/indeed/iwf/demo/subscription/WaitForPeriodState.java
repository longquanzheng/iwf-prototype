package com.indeed.iwf.demo.subscription;

import com.indeed.iwf.StateMovement;
import com.indeed.iwf.WorkflowState;
import com.indeed.iwf.WorkflowStateDecision;
import com.indeed.iwf.condition.ActivityCondition;
import com.indeed.iwf.condition.Prep;
import com.indeed.iwf.condition.SignalCondition;
import com.indeed.iwf.condition.TimerCondition;
import com.indeed.iwf.demo.subscription.models.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.WF_STATE_CHARGE_CURRENT_PERIOD;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.WF_STATE_SEND_WELCOME_EMAIL;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.WF_STATE_SUBSCRIPTION_OVER;

class WaitForPeriodState implements WorkflowState<Void> {

    @Override
    public String getStateId() {
        return WF_STATE_SEND_WELCOME_EMAIL;
    }

    @Override
    public Class<Void> getInputType() {
        return Void.class;
    }

    @Override
    public Prep prepare(final Void nothing, final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {
        final Customer customer = (Customer) queryAttributes.get(QUERY_ATTRIBUTE_CUSTOMER);

        return Prep.prepareAnyConditionCompleted(
                new TimerCondition((int) (System.currentTimeMillis() / 1000) + customer.getSubscription().getPeriodsInSubscription())
        );
    }

    @Override
    public WorkflowStateDecision decide(final Void nothing, final List<ActivityCondition<?>> activityConditions, final List<TimerCondition> timerConditions,
                                        final List<SignalCondition> signalConditions, final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {
        ArrayList<StateMovement> nextStates = new ArrayList();
        final Customer customer = (Customer) queryAttributes.get(QUERY_ATTRIBUTE_CUSTOMER);
        Map<String, Object> attrs = new HashMap<>();
        int currentPeriodNum = (int) queryAttributes.get(QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER);
        if (currentPeriodNum < customer.getSubscription().getPeriodsInSubscription()) {
            attrs.put(QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER, +1); // starting from 0
            nextStates.add(new StateMovement(WF_STATE_CHARGE_CURRENT_PERIOD, null));
        } else {
            nextStates.add(new StateMovement(WF_STATE_SUBSCRIPTION_OVER, null));
        }

        return new WorkflowStateDecision(
                nextStates, null, attrs

        );
    }
}
