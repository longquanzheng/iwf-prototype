package com.iwf.command;

import java.util.Arrays;
import java.util.List;

public class CommandRequest {
    private final List<BaseCommand> commands;
    private final DeciderTriggerType deciderTriggerType;

    private CommandRequest(final List<BaseCommand> commands, final DeciderTriggerType deciderTriggerType) {
        this.commands = commands;
        this.deciderTriggerType = deciderTriggerType;
    }

    public static CommandRequest forAllCommandsCompleted(final BaseCommand... commands) {
        return new CommandRequest(Arrays.asList(commands), DeciderTriggerType.ALL_CONDITION_COMPLETED);
    }

    public static CommandRequest forAnyCommandCompleted(final BaseCommand... commands) {
        return new CommandRequest(Arrays.asList(commands), DeciderTriggerType.ANY_CONDITION_COMPLETED);
    }

    public static CommandRequest forAnyCommandClosed(final BaseCommand... commands) {
        return new CommandRequest(Arrays.asList(commands), DeciderTriggerType.ANY_CONDITION_CLOSED);
    }
}
