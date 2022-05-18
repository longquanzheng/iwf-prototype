package gosdk

import "context"

type WorkflowContext interface {
	context.Context
    GetWorkflowId() string
}
