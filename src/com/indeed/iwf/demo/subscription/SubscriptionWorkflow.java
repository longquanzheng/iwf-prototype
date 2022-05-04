package com.indeed.iwf.demo.subscription;

import com.indeed.iwf.DecodingSourceDataExceptionHandler;
import com.indeed.iwf.QueryAttributeDef;
import com.indeed.iwf.SearchAttributeDef;
import com.indeed.iwf.SignalMethod;
import com.indeed.iwf.Workflow;
import com.indeed.iwf.WorkflowState;
import com.indeed.iwf.demo.subscription.models.Customer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubscriptionWorkflow implements Workflow {
    public static final String WF_STATE_SEND_WELCOME_EMAIL = "sendWelcomeEmail";
    public static final String WF_STATE_CANCEL_SUBSCRIPTION = "cancelSubscription";
    public static final String WF_STATE_WAIT_FOR_NEXT_PERIOD = "waitForNextPeriod";
    public static final String WF_STATE_CHARGE_CURRENT_PERIOD = "chargeCurrentPeriod";
    public static final String WF_STATE_SUBSCRIPTION_OVER = "subscriptionOver";
    public static final String WF_STATE_UPDATE_CHARGE_AMOUNT = "updateChargeAmount";

    public static final String SIGNAL_METHOD_CANCEL_SUBSCRIPTION = "CancelSubscription";
    public static final String SIGNAL_METHOD_UPDATE_BILLING_PERIOD_CHARGE_AMOUNT = "UpdateBillingPeriodChargeAmount";

    public static final String QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER = "BillingPeriodNumber";
    public static final String QUERY_ATTRIBUTE_CUSTOMER = "BillingSubscription";

    @Override
    public List<WorkflowState<?>> getStates() {
        return Arrays.asList(
                new WelcomeEmailState(),
                new CancelSubscriptionState(),
                new WaitForPeriodState(),
                new ChargeCurrentPeriodState(),
                new SubscriptionOverState(),
                new UpdateChargeAmountState()
        );
    }

    @Override
    public List<SignalMethod<?>> getSignalMethods() {
        return Arrays.asList(
                new SignalMethod<>(SIGNAL_METHOD_CANCEL_SUBSCRIPTION, Void.class),
                new SignalMethod<>(SIGNAL_METHOD_UPDATE_BILLING_PERIOD_CHARGE_AMOUNT, Integer.class)
        );
    }

    @Override
    public List<SearchAttributeDef<?>> getSearchAttributes() {
        return Collections.emptyList();
    }

    @Override
    public List<QueryAttributeDef<?>> getQueryAttributes() {
        return Arrays.asList(
                new QueryAttributeDef<>(QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER, Integer.class),
                new QueryAttributeDef<>(QUERY_ATTRIBUTE_CUSTOMER, Customer.class)
        );
    }

    @Override
    public DecodingSourceDataExceptionHandler getDecodingSourceDataExceptionHandler() {
        return null;
    }
}








