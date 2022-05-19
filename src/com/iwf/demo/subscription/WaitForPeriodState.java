package com.iwf.demo.subscription;

import com.iwf.StateDecision;
import com.iwf.StateMovement;
import com.iwf.WorkflowState;
import com.iwf.attributes.QueryAttributesRO;
import com.iwf.attributes.QueryAttributesRW;
import com.iwf.attributes.SearchAttributesRO;
import com.iwf.attributes.SearchAttributesRW;
import com.iwf.command.CommandRequest;
import com.iwf.command.CommandResults;
import com.iwf.command.TimerCommand;
import com.iwf.demo.subscription.models.Customer;

import java.util.ArrayList;

import static com.iwf.demo.subscription.ChargeCurrentPeriodState.WF_STATE_CHARGE_CURRENT_PERIOD;
import static com.iwf.demo.subscription.SubscriptionOverState.WF_STATE_SUBSCRIPTION_OVER;
import static com.iwf.demo.subscription.WelcomeEmailState.WF_STATE_SEND_WELCOME_EMAIL;

class WaitForPeriodState implements WorkflowState<Void> {

    public static final String WF_STATE_WAIT_FOR_NEXT_PERIOD = "waitForNextPeriod";

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
        final Customer customer = queryAttributes.get(SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER);

        return CommandRequest.forAllCommandCompleted(
                new TimerCommand((int) (System.currentTimeMillis() / 1000) + customer.getSubscription().getPeriodsInSubscription())
        );
    }

    @Override
    public StateDecision decide(final Void nothing, final CommandResults commandResults,
                                final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        ArrayList<StateMovement> nextStates = new ArrayList();
        final Customer customer = queryAttributes.get(SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER);
        int currentPeriodNum = queryAttributes.get(SubscriptionWorkflow.QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER);
        if (currentPeriodNum < customer.getSubscription().getPeriodsInSubscription()) {
            queryAttributes.upsert(SubscriptionWorkflow.QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER, currentPeriodNum + 1);
            nextStates.add(new StateMovement(WF_STATE_CHARGE_CURRENT_PERIOD));
        } else {
            nextStates.add(new StateMovement(WF_STATE_SUBSCRIPTION_OVER));
        }

        return new StateDecision(
                nextStates
        );
    }
}
