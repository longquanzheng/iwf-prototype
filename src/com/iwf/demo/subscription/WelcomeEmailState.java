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

class WelcomeEmailState implements WorkflowState<Customer> {

    @Override
    public String getStateId() {
        return SubscriptionWorkflow.WF_STATE_SEND_WELCOME_EMAIL;
    }

    @Override
    public Class<Customer> getInputType() {
        return Customer.class;
    }

    @Override
    public CommandRequest execute(final Customer customer, final SearchAttributesRO searchAttributes, final QueryAttributesRO queryAttributes) {
        return CommandRequest.forAnyCommandCompleted(
                new ActivityCommand<>("SubscriptionActivities::sendWelcomeEmail", Void.class, new ActivityOptions(30), customer)
        );
    }

    @Override
    public StateDecision decide(final Customer customer, final CommandResults commandResults,
                                final SearchAttributesRW searchAttributes, final QueryAttributesRW queryAttributes) {
        queryAttributes.upsert(SubscriptionWorkflow.QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER, 0); // starting from 0
        queryAttributes.upsert(SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER, customer);

        return new StateDecision(
                new StateMovement(SubscriptionWorkflow.WF_STATE_CANCEL_SUBSCRIPTION),
                new StateMovement(SubscriptionWorkflow.WF_STATE_UPDATE_CHARGE_AMOUNT),
                new StateMovement(SubscriptionWorkflow.WF_STATE_WAIT_FOR_NEXT_PERIOD)
        );
    }
}
