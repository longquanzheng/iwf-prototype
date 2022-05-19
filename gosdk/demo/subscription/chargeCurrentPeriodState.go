package subscription

import "github.com/longquanzheng/iwf/gosdk/iwf"

type ChargeCurrentPeriodState struct {

}

func (w ChargeCurrentPeriodState) GetStateId() string {
	panic("implement me")
}

func (w ChargeCurrentPeriodState) GetInputType() iwf.NewTypePtr {
	panic("implement me")
}

func (w ChargeCurrentPeriodState) GetSearchAttributesLoadingPolicy() iwf.AttributeLoadingPolicy {
	panic("implement me")
}

func (w ChargeCurrentPeriodState) GetQueryAttributesLoadingPolicy() iwf.AttributeLoadingPolicy {
	panic("implement me")
}

func (w ChargeCurrentPeriodState) Execute(ctx iwf.WorkflowContext, input interface{}, searchAttributes iwf.SearchAttributesRO, queryAttributes iwf.QueryAttributesRO) (iwf.CommandRequest, error) {
	panic("implement me")
}

func (w ChargeCurrentPeriodState) Decide(ctx iwf.WorkflowContext, input interface{}, commandResults iwf.CommandResults, searchAttributes iwf.SearchAttributesRW, queryAttributes iwf.QueryAttributesRW) (iwf.StateDecision, error) {
	panic("implement me")
}

var _ iwf.WorkflowState = (*ChargeCurrentPeriodState)(nil)