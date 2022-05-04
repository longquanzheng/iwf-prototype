package com.indeed.iwf.demo.subscription;

import com.indeed.iwf.StateMovement;
import com.indeed.iwf.WorkflowState;
import com.indeed.iwf.WorkflowStateDecision;
import com.indeed.iwf.condition.ActivityCondition;
import com.indeed.iwf.condition.ActivityOptions;
import com.indeed.iwf.condition.Prep;
import com.indeed.iwf.condition.SignalCondition;
import com.indeed.iwf.condition.TimerCondition;
import com.indeed.iwf.demo.subscription.models.Customer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.WF_STATE_CANCEL_SUBSCRIPTION;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.WF_STATE_SEND_WELCOME_EMAIL;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.WF_STATE_UPDATE_CHARGE_AMOUNT;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.WF_STATE_WAIT_FOR_NEXT_PERIOD;

class WelcomeEmailState implements WorkflowState<Customer> {

    @Override
    public String getStateId() {
        return WF_STATE_SEND_WELCOME_EMAIL;
    }

    @Override
    public Class<Customer> getInputType() {
        return Customer.class;
    }

    @Override
    public Prep prepare(final Customer customer, final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {
        return Prep.prepareAnyConditionCompleted(
                new ActivityCondition<>("SubscriptionActivities::sendWelcomeEmail", Void.class, new ActivityOptions(30), customer)
        );
    }

    @Override
    public WorkflowStateDecision decide(final Customer customer, final List<ActivityCondition<?>> activityConditions, final List<TimerCondition> timerConditions, final List<SignalCondition> signalConditions, final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {
        Map<String, Object> attrs = new HashMap<>();
        attrs.put(QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER, 0); // starting from 0
        attrs.put(QUERY_ATTRIBUTE_CUSTOMER, customer);

        return new WorkflowStateDecision(
                Arrays.asList(
                        new StateMovement(WF_STATE_CANCEL_SUBSCRIPTION, null),
                        new StateMovement(WF_STATE_UPDATE_CHARGE_AMOUNT, null),
                        new StateMovement(WF_STATE_WAIT_FOR_NEXT_PERIOD, null)
                ), null, attrs

        );
    }
}
