package subscription

import "github.com/longquanzheng/iwf/gosdk/iwf"

type CancelSubscriptionState struct {

}

func (w CancelSubscriptionState) GetStateId() string {
	panic("implement me")
}

func (w CancelSubscriptionState) GetInputType() interface{} {
	panic("implement me")
}

func (w CancelSubscriptionState) GetSearchAttributesLoadingPolicy() iwf.AttributeLoadingPolicy {
	panic("implement me")
}

func (w CancelSubscriptionState) GetQueryAttributesLoadingPolicy() iwf.AttributeLoadingPolicy {
	panic("implement me")
}

func (w CancelSubscriptionState) Execute(ctx iwf.WorkflowContext, input interface{}, searchAttributes iwf.SearchAttributesRO, queryAttributes iwf.QueryAttributesRO) iwf.CommandRequest {
	panic("implement me")
}

func (w CancelSubscriptionState) Decide(ctx iwf.WorkflowContext, input interface{}, commandResults iwf.CommandResults, searchAttributes iwf.SearchAttributesRW, queryAttributes iwf.QueryAttributesRW) iwf.StateDecision {
	panic("implement me")
}

var _ iwf.WorkflowState = (*CancelSubscriptionState)(nil)