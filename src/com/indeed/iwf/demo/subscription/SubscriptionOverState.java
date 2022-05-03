package com.indeed.iwf.demo.subscription;

import com.indeed.iwf.WorkflowState;
import com.indeed.iwf.WorkflowStateDecision;
import com.indeed.iwf.condition.ActivityCondition;
import com.indeed.iwf.condition.ActivityOptions;
import com.indeed.iwf.condition.BaseCondition;
import com.indeed.iwf.condition.SignalCondition;
import com.indeed.iwf.condition.TimerCondition;
import com.indeed.iwf.demo.subscription.models.Customer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.indeed.iwf.StateMovement.COMPLETING_WORKFLOW;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER;
import static com.indeed.iwf.demo.subscription.SubscriptionWorkflow.WF_STATE_SUBSCRIPTION_OVER;

class SubscriptionOverState implements WorkflowState<Void> {

    @Override
    public String getStateId() {
        return WF_STATE_SUBSCRIPTION_OVER;
    }

    @Override
    public Class<Void> getInputType() {
        return Void.class;
    }

    @Override
    public List<BaseCondition> prepare(final Void nothing, final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {
        final Customer customer = (Customer) queryAttributes.get(QUERY_ATTRIBUTE_CUSTOMER);
        return Arrays.asList(
                new ActivityCondition<>("SubscriptionActivities::sendSubscriptionOverEmail", Void.class, new ActivityOptions(30), customer)
        );
    }

    @Override
    public WorkflowStateDecision decide(final Void nothing, final List<ActivityCondition<?>> activityConditions, final List<TimerCondition> timerConditions,
                                        final List<SignalCondition> signalConditions, final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {
        return new WorkflowStateDecision(
                Arrays.asList(
                        COMPLETING_WORKFLOW
                ), null, null

        );
    }
}