package com.indeed.iwf;

import java.util.List;

/**
 * This is a simplified Cadence/Temporal workflow. All the complexity of
 * history replay and decision task processing are hidden. No matter how you modify the workflow code, it will never run
 * into non-deterministic errors. The signal/timer/activities will be defined into a way that you don't need to understand
 * what is a workflow decision task and how it's executed.
 * It preserves the capabilities of executed activities, setting timers, processing signals, upsert search attributes, and
 * setting query methods. So basically, you still have the full power of using Cadence/Temporal, without needing to understand
 * the complex technology.
 * The workflow is still defined as code but in a different way. Instead of having a whole piece of workflow method to define the
 * workflow code, you will have to split the logic and place into different states.
 *
 * @param <I> the input type of the workflow
 */
public class Workflow<I> {

    /**
     * This is the minimum to define a workflow
     *
     * @param inputType the class type of the input
     * @param states    defines the states of the workflow. A state represents a step of the workflow state machine.
     *                  A state can execute some activities and wait for result, set timers to wait for firing, wait for signals.
     *                  Upon any of the conditions is met, a callback of the state will be executed to decide what to do next(wait for more conditions, or go to next state).
     *                  See more details in the state definition.
     */
    public Workflow(final Class<I> inputType, final List<WorkflowState> states) {
        this.inputType = inputType;
        this.states = states;
    }

    /**
     * @param signalMethods register all the signal methods supported by this workflow.
     */
    public Workflow(final Class<I> inputType, final List<WorkflowState> states, final List<SignalMethod> signalMethods) {
        new Workflow(inputType, states);
        this.signalMethods = signalMethods;
    }

    /**
     * @param queryMethods defines all the query methods supported by this workflow.
     *                     NOTE: different from Cadence/Temporal, a query method is not executed throw Cadence/Temporal workflow worker.
     *                     It won't run into non-deterministic issues, no matter how you modify it.
     */
    public Workflow(final Class<I> inputType, final List<WorkflowState> states, final List<SignalMethod> signalMethods, final List<QueryMethod> queryMethods) {
        new Workflow(inputType, states, signalMethods);
        this.queryMethods = queryMethods;
    }

    /**
     * Because the output type can be changed which causes data cannot be decoded to the new type.
     * Implement this handler is to fix this in-compatible change so that the data can still be decoded to the old type.
     *
     * @param handler the handler to handle the exception
     */
    public Workflow(final Class<I> inputType, final List<WorkflowState> states, final List<SignalMethod> signalMethods, final List<QueryMethod> queryMethods, DecodingSourceDataExceptionHandler handler) {
        new Workflow(inputType, states, signalMethods, queryMethods);
        this.handler = handler;
    }

    // NOTE: This input type of the workflow is required because SDK need to decode the data using dataConverter
    private Class<I> inputType;

    private List<WorkflowState> states;
    private List<SignalMethod> signalMethods;
    private List<QueryMethod> queryMethods;
    private DecodingSourceDataExceptionHandler handler;

    Class<I> getInputType() {
        return inputType;
    }

    public List<WorkflowState> getStates() {
        return states;
    }

    public List<SignalMethod> getSignalMethods() {
        return signalMethods;
    }

    public List<QueryMethod> getQueryMethods() {
        return queryMethods;
    }

    public DecodingSourceDataExceptionHandler getDecodingSourceDataExceptionHandler() {
        return handler;
    }
}

