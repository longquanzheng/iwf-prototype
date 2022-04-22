package com.indeed.iwf.demo;

import com.indeed.iwf.DecodingSourceDataExceptionHandler;
import com.indeed.iwf.QueryAttribute;
import com.indeed.iwf.SearchAttribute;
import com.indeed.iwf.SignalMethod;
import com.indeed.iwf.StateMovement;
import com.indeed.iwf.Workflow;
import com.indeed.iwf.WorkflowState;
import com.indeed.iwf.WorkflowStateDecision;
import com.indeed.iwf.condition.ActivityCondition;
import com.indeed.iwf.condition.ActivityOptions;
import com.indeed.iwf.condition.BaseCondition;
import com.indeed.iwf.condition.SignalCondition;
import com.indeed.iwf.condition.TimerCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.indeed.iwf.StateMovement.COMPLETING_WORKFLOW;
import static com.indeed.iwf.demo.SubscriptionWorkflow.QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER;
import static com.indeed.iwf.demo.SubscriptionWorkflow.QUERY_ATTRIBUTE_CUSTOMER;
import static com.indeed.iwf.demo.SubscriptionWorkflow.WF_STATE_CANCEL_SUBSCRIPTION;
import static com.indeed.iwf.demo.SubscriptionWorkflow.WF_STATE_CHARGE_CURRENT_PERIOD;
import static com.indeed.iwf.demo.SubscriptionWorkflow.WF_STATE_SEND_SUBSCRIPTION_OVER_EMAIL;
import static com.indeed.iwf.demo.SubscriptionWorkflow.WF_STATE_SEND_WELCOME_EMAIL;
import static com.indeed.iwf.demo.SubscriptionWorkflow.WF_STATE_UPDATE_CHARGE_AMOUNT;
import static com.indeed.iwf.demo.SubscriptionWorkflow.WF_STATE_WAIT_FOR_NEXT_PERIOD;

public class SubscriptionWorkflow implements Workflow {
    public static final String WF_STATE_SEND_WELCOME_EMAIL = "sendWelcomeEmail";
    public static final String WF_STATE_CANCEL_SUBSCRIPTION = "cancelSubscription";
    public static final String WF_STATE_WAIT_FOR_NEXT_PERIOD = "waitForNextPeriod";
    public static final String WF_STATE_CHARGE_CURRENT_PERIOD = "chargeCurrentPeriod";
    public static final String WF_STATE_SEND_SUBSCRIPTION_OVER_EMAIL = "sendSubscriptionOverEmail";
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
    public List<SearchAttribute<?>> getSearchAttributes() {
        return Collections.emptyList();
    }

    @Override
    public List<QueryAttribute<?>> getQueryAttributes() {
        return Arrays.asList(
                new QueryAttribute<>(QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER, Integer.class),
                new QueryAttribute<>(QUERY_ATTRIBUTE_CUSTOMER, Customer.class)
        );
    }

    @Override
    public DecodingSourceDataExceptionHandler getDecodingSourceDataExceptionHandler() {
        return null;
    }
}

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
    public List<BaseCondition> prepare(final Customer customer, final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {
        return Arrays.asList(
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

class CancelSubscriptionState implements WorkflowState<Void> {

    @Override
    public String getStateId() {
        return WF_STATE_CANCEL_SUBSCRIPTION;
    }

    @Override
    public Class<Void> getInputType() {
        return Void.class;
    }

    @Override
    public List<BaseCondition> prepare(final Void nothing, final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {
        return Arrays.asList(
                new SignalCondition(SubscriptionWorkflow.SIGNAL_METHOD_CANCEL_SUBSCRIPTION)
        );
    }

    @Override
    public WorkflowStateDecision decide(final Void nothing, final List<ActivityCondition<?>> activityConditions,
                                        final List<TimerCondition> timerConditions, final List<SignalCondition> signalConditions,
                                        final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {
        return new WorkflowStateDecision(
                Arrays.asList(
                        COMPLETING_WORKFLOW
                ), null, null
        );
    }
}

class UpdateChargeAmountState implements WorkflowState<Void> {

    @Override
    public String getStateId() {
        return WF_STATE_UPDATE_CHARGE_AMOUNT;
    }

    @Override
    public Class<Void> getInputType() {
        return Void.class;
    }

    @Override
    public List<BaseCondition> prepare(final Void nothing, final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {
        return Arrays.asList(
                new SignalCondition(SubscriptionWorkflow.SIGNAL_METHOD_UPDATE_BILLING_PERIOD_CHARGE_AMOUNT)
        );
    }

    @Override
    public WorkflowStateDecision decide(final Void nothing, final List<ActivityCondition<?>> activityConditions,
                                        final List<TimerCondition> timerConditions, final List<SignalCondition> signalConditions,
                                        final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {

        final Map<String, Object> attrs = new HashMap<>();
        int newAmount = (int) signalConditions.get(0).getSignalValue();
        final Customer customer = (Customer) queryAttributes.get(QUERY_ATTRIBUTE_CUSTOMER);
        customer.getSubscription().setBillingPeriodCharge(newAmount);
        attrs.put(QUERY_ATTRIBUTE_CUSTOMER, customer);

        return new WorkflowStateDecision(

                Arrays.asList(
                        new StateMovement(WF_STATE_UPDATE_CHARGE_AMOUNT, null) // go to a loop to update the value
                ), null, attrs
        );
    }
}

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
    public List<BaseCondition> prepare(final Void nothing, final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {
        final Customer customer = (Customer) queryAttributes.get(QUERY_ATTRIBUTE_CUSTOMER);

        return Arrays.asList(
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
            nextStates.add(new StateMovement(WF_STATE_SEND_SUBSCRIPTION_OVER_EMAIL, null));
        }

        return new WorkflowStateDecision(
                nextStates, null, attrs

        );
    }
}

class ChargeCurrentPeriodState implements WorkflowState<Void> {

    @Override
    public String getStateId() {
        return WF_STATE_CHARGE_CURRENT_PERIOD;
    }

    @Override
    public Class<Void> getInputType() {
        return Void.class;
    }

    @Override
    public List<BaseCondition> prepare(final Void nothing, final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {
        final Customer customer = (Customer) queryAttributes.get(QUERY_ATTRIBUTE_CUSTOMER);
        final int currentPeriod = (int) queryAttributes.get(QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER);
        return Arrays.asList(
                new ActivityCondition<>("SubscriptionActivities::chargeCustomerForBillingPeriod", Void.class, new ActivityOptions(30), customer, currentPeriod)
        );
    }

    @Override
    public WorkflowStateDecision decide(final Void nothing, final List<ActivityCondition<?>> activityConditions, final List<TimerCondition> timerConditions,
                                        final List<SignalCondition> signalConditions, final Map<String, Object> searchAttributes, final Map<String, Object> queryAttributes) {
        return new WorkflowStateDecision(
                Arrays.asList(
                        new StateMovement(WF_STATE_WAIT_FOR_NEXT_PERIOD, null)
                ), null, null

        );
    }
}

class SubscriptionOverState implements WorkflowState<Void> {

    @Override
    public String getStateId() {
        return WF_STATE_SEND_SUBSCRIPTION_OVER_EMAIL;
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