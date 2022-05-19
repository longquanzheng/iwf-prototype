package iwf

type SignalMethodDef interface {
	GetName() string
	GetValueType() interface{}
}

type SignalCommand interface {
	BaseCommand
	GetName() string
}

type SignalStatus string

const (
	REQUESTED SignalStatus = "REQUESTED"
	RECEIVED  SignalStatus = "RECEIVED"
)

type SignalCommandResult interface {
	GetCommandId() string
	GetName() string
	GeValue() interface{}
	GetSignalStatus() SignalStatus
}
