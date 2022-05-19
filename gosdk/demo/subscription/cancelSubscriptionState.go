package subscription

import "github.com/longquanzheng/iwf/gosdk/iwf"

type WelcomeEmailState struct {

}

func (w WelcomeEmailState) GetStateId() string {
	panic("implement me")
}

func (w WelcomeEmailState) GetInputType() interface{} {
	panic("implement me")
}

func (w WelcomeEmailState) GetSearchAttributesLoadingPolicy() iwf.AttributeLoadingPolicy {
	panic("implement me")
}

func (w WelcomeEmailState) GetQueryAttributesLoadingPolicy() iwf.AttributeLoadingPolicy {
	panic("implement me")
}

func (w WelcomeEmailState) Execute(ctx iwf.WorkflowContext, input interface{}, searchAttributes iwf.SearchAttributesRO, queryAttributes iwf.QueryAttributesRO) iwf.CommandRequest {
	panic("implement me")
}

func (w WelcomeEmailState) Decide(ctx iwf.WorkflowContext, input interface{}, commandResults iwf.CommandResults, searchAttributes iwf.SearchAttributesRW, queryAttributes iwf.QueryAttributesRW) iwf.StateDecision {
	panic("implement me")
}

var _ iwf.WorkflowState = (*WelcomeEmailState)(nil)