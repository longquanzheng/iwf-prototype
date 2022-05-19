package iwf

type CommandRequest interface {
	GetCommands() []BaseCommand
	GetDeciderTriggerType() DeciderTriggerType
}

func WaitForAllCommandsCompleted(command ...BaseCommand)CommandRequest{
	return nil
}

func WaitForAnyCommandsCompleted(command ...BaseCommand)CommandRequest{
	return nil
}

func WaitForAnyCommandsClosed(command ...BaseCommand)CommandRequest{
	return nil
}

type CommandResults interface {
	GetAllActivityCommandResults() []ActivityCommandResult
	GetAllSignalCommandResults() []SignalCommandResult
	GetAllTimerCommandResults() []TimerCommandResult
	GetActivityOutputByIndex(idx int) interface{}
	GetSignalValueByIndex(idx int) interface{}
	GetActivityOutputById(id string) interface{}
	GetSignalValueById(id string) interface{}
	GetActivityCommandResultByIndex(idx int) ActivityCommandResult
	GetActivityCommandResultById(id string) ActivityCommandResult
	GetSignalCommandResultByIndex(idx int) SignalCommandResult
	GetSignalCommandResultById(id string) SignalCommandResult
}

type BaseCommand interface {
	GetCommandId() string
}

type DeciderTriggerType string

const (
	ALL_COMMAND_COMPLETED DeciderTriggerType = "ALL_COMMAND_COMPLETED" // this will wait for all commands are completed. It will fail the workflow if any command fails(e.g. activity failure)
	ANY_COMMAND_COMPLETED DeciderTriggerType = "ANY_COMMAND_COMPLETED" // this will wait for any command to be completed. It will fail the workflow if any command fails(e.g. activity failure)
	ANY_COMMAND_CLOSED    DeciderTriggerType = "ANY_COMMAND_CLOSED"    // this will wait for any command to be closed. It won't fail the workflow if any command fails(e.g. activity failure)
)
