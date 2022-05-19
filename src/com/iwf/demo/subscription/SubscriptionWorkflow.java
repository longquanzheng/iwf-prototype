package com.iwf.demo.subscription;

import com.iwf.StateDef;
import com.iwf.Workflow;
import com.iwf.attributes.QueryAttributeDef;
import com.iwf.attributes.SearchAttributeDef;
import com.iwf.command.SignalMethodDef;
import com.iwf.demo.subscription.models.Customer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubscriptionWorkflow implements Workflow {

    public static final String SIGNAL_METHOD_CANCEL_SUBSCRIPTION = "CancelSubscription";
    public static final String SIGNAL_METHOD_UPDATE_BILLING_PERIOD_CHARGE_AMOUNT = "UpdateBillingPeriodChargeAmount";

    public static final String QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER = "BillingPeriodNumber";
    public static final String QUERY_ATTRIBUTE_CUSTOMER = "BillingSubscription";

    @Override
    public List<StateDef> getStates() {
        return Arrays.asList(
                new StateDef(new WelcomeEmailState(), true),
                new StateDef(new CancelSubscriptionState(), false),
                new StateDef(new WaitForPeriodState(), false),
                new StateDef(new ChargeCurrentPeriodState(), false),
                new StateDef(new SubscriptionOverState(), false),
                new StateDef(new UpdateChargeAmountState(), false)
        );
    }

    @Override
    public List<SignalMethodDef<?>> getSignalMethods() {
        return Arrays.asList(
                new SignalMethodDef<>(SIGNAL_METHOD_CANCEL_SUBSCRIPTION, Void.class),
                new SignalMethodDef<>(SIGNAL_METHOD_UPDATE_BILLING_PERIOD_CHARGE_AMOUNT, Integer.class)
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
}








