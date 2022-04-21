package com.indeed.iwf.demo;

import com.indeed.iwf.DecodingSourceDataExceptionHandler;
import com.indeed.iwf.QueryAttribute;
import com.indeed.iwf.SearchAttribute;
import com.indeed.iwf.SignalMethod;
import com.indeed.iwf.Workflow;
import com.indeed.iwf.WorkflowState;

import java.util.List;

public class SubscriptionWorkflow implements Workflow {
    @Override
    public List<WorkflowState<?>> getStates() {
        return null;
    }

    @Override
    public List<SignalMethod<?>> getSignalMethods() {
        return null;
    }

    @Override
    public List<SearchAttribute<?>> getSearchAttributes() {
        return null;
    }

    @Override
    public List<QueryAttribute<?>> getQueryAttributes() {
        return null;
    }

    @Override
    public DecodingSourceDataExceptionHandler getDecodingSourceDataExceptionHandler() {
        return null;
    }
}
