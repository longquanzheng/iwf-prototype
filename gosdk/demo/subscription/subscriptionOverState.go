package subscription

import "github.com/longquanzheng/iwf/gosdk/iwf"

type SubscriptioinOverState struct {

}

func (w SubscriptioinOverState) GetStateId() string {
	panic("implement me")
}

func (w SubscriptioinOverState) GetInputType() iwf.NewTypePtr {
	panic("implement me")
}

func (w SubscriptioinOverState) GetSearchAttributesLoadingPolicy() iwf.AttributeLoadingPolicy {
	panic("implement me")
}

func (w SubscriptioinOverState) GetQueryAttributesLoadingPolicy() iwf.AttributeLoadingPolicy {
	panic("implement me")
}

func (w SubscriptioinOverState) Execute(ctx iwf.WorkflowContext, input interface{}, searchAttributes iwf.SearchAttributesRO, queryAttributes iwf.QueryAttributesRO) (iwf.CommandRequest, error) {
	panic("implement me")
}

func (w SubscriptioinOverState) Decide(ctx iwf.WorkflowContext, input interface{}, commandResults iwf.CommandResults, searchAttributes iwf.SearchAttributesRW, queryAttributes iwf.QueryAttributesRW) (iwf.StateDecision, error) {
	panic("implement me")
}

var _ iwf.WorkflowState = (*SubscriptioinOverState)(nil)