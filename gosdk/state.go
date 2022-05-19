package gosdk

type StateDef interface {
	getState() WorkflowState
	isStartable() bool
}

type WorkflowState interface {
	GetStateId() string
	/**
	 * return a struct pointer of the input type
	 * this is needed for deserializing data into the input object for execute/decide API before invoking them
	 * TODO: think of a better way to do it in Golang, maybe using generic?
	 */
	GetInputType() interface{}
	GetSearchAttributesLoadingPolicy() AttributeLoadingPolicy
	GetQueryAttributesLoadingPolicy() AttributeLoadingPolicy

	/**
	 * Implement this method to execute the commands set up for this state.
	 *
	 * @param input            the state input which is deserialized by dataConverter with {@link #getInputType}
	 * @param queryAttributes  the query attributes that can be used as readOnly
	 * @param searchAttributes the search attributes that can be used as readOnly
	 * @return the requested commands for this step
	 * NOTE: it's readonly here for simplifying the implementation(execute can be reverted in some edge cases),
	 *       We could change to support R+W if necessary.
	 */
	execute(ctx WorkflowContext, input interface{}, searchAttributes SearchAttributesRO, queryAttributes QueryAttributesRO) CommandRequest

	/**
	 * Implement this method to decide what to do next when requested commands are ready
	 *
	 * @param input            the state input which is deserialized by dataConverter with {@link #getInputType}
	 * @param commandResults   the results of the command that executed by {@link #execute}
	 * @param queryAttributes  the query attributes that can be used as Read+Write
	 * @param searchAttributes the search attributes that can be used as Read+Write
	 * @return the decision of what to do next(e.g. transition to next states)
	 */
	decide(ctx WorkflowContext, input interface{}, commandResults CommandResults, searchAttributes SearchAttributesRW, queryAttributes QueryAttributesRW) StateDecision
}

type StateDecision interface {
	WaitForMoreCommandResults() bool
	GetNextStates() []StateMovement
	GetUpsertSearchAttributes()
	GetUpsertQueryAttributes()
}

type StateMovement interface {
	GetNextStateId() string
	GetNextStateInput() interface{}
}

type builtInStateMovement struct {
	id string
}

func (m *builtInStateMovement) GetNextStateId() string {
	return m.id
}

func (m *builtInStateMovement) GetNextStateInput() interface{} {
	return nil
}

func CompletingWorkflow() StateMovement {
	return &builtInStateMovement{
		id: "_SYS_COMPLETING_WORKFLOW",
	}
}

func FailingWorkflow() StateMovement {
	return &builtInStateMovement{
		id: "_SYS_FAILING_WORKFLOW",
	}

}
