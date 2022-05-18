package gosdk

type WorkflowState interface {
	GetStateId() string
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

var COMPLETING_WORKFLOW = &builtInStateMovement{
	id: "_SYS_COMPLETING_WORKFLOW",
}

var FAILING_WORKFLOW = &builtInStateMovement{
	id: "_SYS_FAILING_WORKFLOW",
}
