package com.iwf.demo.subscription;

import com.iwf.StateDecision;
import com.iwf.StateMovement;
import com.iwf.WorkflowState;
import com.iwf.attributes.QueryAttributesRO;
import com.iwf.attributes.QueryAttributesRW;
import com.iwf.attributes.SearchAttributesRO;
import com.iwf.attributes.SearchAttributesRW;
import com.iwf.command.ActivityCommand;
import com.iwf.command.ActivityOptions;
import com.iwf.command.CommandRequest;
import com.iwf.command.CommandResults;
import com.iwf.demo.subscription.models.Customer;

import static com.iwf.demo.subscription.CancelSubscriptionState.WF_STATE_CANCEL_SUBSCRIPTION;
import static com.iwf.demo.subscription.UpdateChargeAmountState.WF_STATE_UPDATE_CHARGE_AMOUNT;
import static com.iwf.demo.subscription.WaitForPeriodState.WF_STATE_WAIT_FOR_NEXT_PERIOD;

class WelcomeEmailState implements WorkflowState<Customer> {

    public static final String WF_STATE_SEND_WELCOME_EMAIL = "sendWelcomeEmail";

    @Override
    public String getStateId() {
        return WF_STATE_SEND_WELCOME_EMAIL;
    }

    @Override
    public Class<Customer> getInputType() {
        return Customer.class;
    }

    @Override
    public CommandRequest execute(final Customer customer, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttributes) {
        return CommandRequest.forAllCommandCompleted(
                new ActivityCommand<>("SubscriptionActivities::sendWelcomeEmail", Void.class, new ActivityOptions(30), customer)
        );
    }

    @Override
    public StateDecision decide(final Customer customer, final CommandResults commandResults,
                                final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        queryAttributes.upsert(SubscriptionWorkflow.QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER, 0); // starting from 0
        queryAttributes.upsert(SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER, customer);

        return new StateDecision(
                new StateMovement(WF_STATE_CANCEL_SUBSCRIPTION),
                new StateMovement(WF_STATE_UPDATE_CHARGE_AMOUNT),
                new StateMovement(WF_STATE_WAIT_FOR_NEXT_PERIOD)
        );
    }
}
