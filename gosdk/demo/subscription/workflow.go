package subscription

import "github.com/longquanzheng/iwf/gosdk/iwf"

const SIGNAL_METHOD_CANCEL_SUBSCRIPTION = "CancelSubscription"
const SIGNAL_METHOD_UPDATE_BILLING_PERIOD_CHARGE_AMOUNT = "UpdateBillingPeriodChargeAmount"

const QUERY_ATTRIBUTE_BILLING_PERIOD_NUMBER = "BillingPeriodNumber"
const QUERY_ATTRIBUTE_CUSTOMER = "BillingSubscription"

const SEND_WELCOME_EMAIL_ACTIVITY = "SubscriptionActivities::sendWelcomeEmail"
const SEND_SUBSCRIPTION_OVER_EMAIL_ACTIVITY = "SubscriptionActivities::sendSubscriptionOverEmail"
const CHARGE_CUSTOMER_ACTIVITY = "SubscriptionActivities::chargeCustomerForBillingPeriod"

type SubscriptionWorkflow struct {
}

func (s SubscriptionWorkflow) GetStates() []iwf.StateDef {
	panic("implement me")
}

func (s SubscriptionWorkflow) GetActivityTypes() []iwf.ActivityTypeDef {
	panic("implement me")
}

func (s SubscriptionWorkflow) GetSignalMethods() []iwf.SignalMethodDef {
	panic("implement me")
}

func (s SubscriptionWorkflow) GetSearchAttributes() []iwf.SearchAttributeDef {
	panic("implement me")
}

func (s SubscriptionWorkflow) GetQueryAttributes() []iwf.QueryAttributeDef {
	panic("implement me")
}

var _ iwf.Workflow = (*SubscriptionWorkflow)(nil)
