package com.iwf;

import com.iwf.attributes.AttributeLoadingPolicy;
import com.iwf.command.BaseCommand;
import com.iwf.command.CommandCarryOverPolicy;

public class StateOptions {

    private final AttributeLoadingPolicy searchAttributesLoadingPolicy;

    private final AttributeLoadingPolicy queryAttributesLoadingPolicy;

    private final CommandCarryOverPolicy commandCarryOverPolicy;

    public StateOptions(final AttributeLoadingPolicy searchAttributesLoadingPolicy, final AttributeLoadingPolicy queryAttributesLoadingPolicy, final CommandCarryOverPolicy commandCarryOverPolicy) {
        this.searchAttributesLoadingPolicy = searchAttributesLoadingPolicy;
        this.queryAttributesLoadingPolicy = queryAttributesLoadingPolicy;
        this.commandCarryOverPolicy = commandCarryOverPolicy;
    }

    /**
     * when using {@link com.iwf.command.CommandRequest#forAnyCommandClosed or {@link com.iwf.command.CommandRequest#forAnyCommandsCompleted(BaseCommand...)}
     * there could be some unfinished commands left to this state. This policy decided whether and how to carry over those unfinished command to
     * future states. Default to {@link com.iwf.command.CommandCarryOverType#NONE} which means no carry over.
     */
    public CommandCarryOverPolicy getCommandCarryOverPolicy() {
        return commandCarryOverPolicy;
    }

    /**
     * this decides whether to load all the query attributes into {@link WorkflowState#decide} and {@link WorkflowState#start} method
     * default to true
     */
    public AttributeLoadingPolicy getQueryAttributesLoadingPolicy() {
        return queryAttributesLoadingPolicy;
    }

    /**
     * this decides whether to load all the search attributes into {@link WorkflowState#decide} and {@link WorkflowState#start} method
     * default to true
     */
    public AttributeLoadingPolicy getSearchAttributesLoadingPolicy() {
        return searchAttributesLoadingPolicy;
    }
}
