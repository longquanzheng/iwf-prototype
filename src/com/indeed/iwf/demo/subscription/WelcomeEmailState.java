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
import com.indeed.iwf.condition.ConditionResults;
import com.indeed.iwf.condition.Prep;
import com.indeed.iwf.demo.subscription.models.Customer;

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
    public Prep prepare(final Customer customer, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttributes) {
        return Prep.prepareAnyConditionCompleted(
                new ActivityCondition<>("SubscriptionActivities::sendWelcomeEmail", Void.class, new ActivityOptions(30), customer)
        );
    }

    @Override
    public WorkflowStateDecision decide(final Customer customer, final ConditionResults conditionResults,
                                        final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        queryAttributes.upsert(QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER, 0); // starting from 0
        queryAttributes.upsert(QUERY_ATTRIBUTE_CUSTOMER, customer);

        return new WorkflowStateDecision(
                new StateMovement(WF_STATE_CANCEL_SUBSCRIPTION),
                new StateMovement(WF_STATE_UPDATE_CHARGE_AMOUNT),
                new StateMovement(WF_STATE_WAIT_FOR_NEXT_PERIOD)
        );
    }
}
