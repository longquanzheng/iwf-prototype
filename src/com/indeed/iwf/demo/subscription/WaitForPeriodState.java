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
import com.indeed.iwf.command.TimerCommand;
import com.indeed.iwf.demo.subscription.models.Customer;

import java.util.ArrayList;

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
    public CommandRequest execute(final Void nothing, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttributes) {
        final Customer customer = queryAttributes.get(QUERY_ATTRIBUTE_CUSTOMER);

        return CommandRequest.forAnyCommandCompleted(
                new TimerCommand((int) (System.currentTimeMillis() / 1000) + customer.getSubscription().getPeriodsInSubscription())
        );
    }

    @Override
    public StateDecision decide(final Void nothing, final CommandResults commandResults,
                                final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        ArrayList<StateMovement> nextStates = new ArrayList();
        final Customer customer = queryAttributes.get(QUERY_ATTRIBUTE_CUSTOMER);
        int currentPeriodNum = queryAttributes.get(QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER);
        if (currentPeriodNum < customer.getSubscription().getPeriodsInSubscription()) {
            queryAttributes.upsert(QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER, +1); // starting from 0
            nextStates.add(new StateMovement(WF_STATE_CHARGE_CURRENT_PERIOD));
        } else {
            nextStates.add(new StateMovement(WF_STATE_SUBSCRIPTION_OVER));
        }

        return new StateDecision(
                nextStates
        );
    }
}
