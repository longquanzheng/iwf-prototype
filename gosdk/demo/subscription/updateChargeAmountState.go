package subscription

import "github.com/longquanzheng/iwf/gosdk/iwf"

type UpdateChargeAmountState struct {

}

func (w UpdateChargeAmountState) GetStateId() string {
	panic("implement me")
}

func (w UpdateChargeAmountState) GetInputType() iwf.NewTypePtr {
	panic("implement me")
}

func (w UpdateChargeAmountState) GetSearchAttributesLoadingPolicy() iwf.AttributeLoadingPolicy {
	panic("implement me")
}

func (w UpdateChargeAmountState) GetQueryAttributesLoadingPolicy() iwf.AttributeLoadingPolicy {
	panic("implement me")
}

func (w UpdateChargeAmountState) Execute(ctx iwf.WorkflowContext, input interface{}, searchAttributes iwf.SearchAttributesRO, queryAttributes iwf.QueryAttributesRO) (iwf.CommandRequest, error) {
	panic("implement me")
}

func (w UpdateChargeAmountState) Decide(ctx iwf.WorkflowContext, input interface{}, commandResults iwf.CommandResults, searchAttributes iwf.SearchAttributesRW, queryAttributes iwf.QueryAttributesRW) (iwf.StateDecision, error) {
	panic("implement me")
}

var _ iwf.WorkflowState = (*UpdateChargeAmountState)(nil)