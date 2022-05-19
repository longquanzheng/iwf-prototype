package subscription

import (
	"fmt"
	"time"

	"github.com/longquanzheng/iwf/gosdk/iwf"
)

type WaitForPeriodState struct {

}
const WF_STATE_WAIT_FOR_NEXT_PERIOD = "waitForNextPeriod"

func (w WaitForPeriodState) GetStateId() string {
	return WF_STATE_WAIT_FOR_NEXT_PERIOD
}

func (w WaitForPeriodState) GetInputType() iwf.NewTypePtr {
	return nil
}

func (w WaitForPeriodState) GetSearchAttributesLoadingPolicy() iwf.AttributeLoadingPolicy {
	return nil
}

func (w WaitForPeriodState) GetQueryAttributesLoadingPolicy() iwf.AttributeLoadingPolicy {
	return nil
}

func (w WaitForPeriodState) Execute(ctx iwf.WorkflowContext, input interface{}, searchAttributes iwf.SearchAttributesRO, queryAttributes iwf.QueryAttributesRO) (iwf.CommandRequest, error){
	customer, ok := input.(*Customer)
	if !ok{
		return nil, fmt.Errorf("cannot get customer from input")
	}
	waitTime := time.Duration(customer.Subscription.BillingPeriodInSeconds) * time.Second
	return iwf.WaitForAllCommandsCompleted(
		iwf.NewTimerCommand(time.Now().Add(waitTime)),
	), nil
}

func (w WaitForPeriodState) Decide(ctx iwf.WorkflowContext, input interface{}, commandResults iwf.CommandResults, searchAttributes iwf.SearchAttributesRW, queryAttributes iwf.QueryAttributesRW) (iwf.StateDecision, error) {
	panic("implement me")
}

var _ iwf.WorkflowState = (*WaitForPeriodState)(nil)