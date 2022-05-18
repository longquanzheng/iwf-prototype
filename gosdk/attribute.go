package gosdk

type SearchAttributeDef interface {
	getKey() string
	getValueType() interface{}
}

type QueryAttributeDef interface {
	getName() string
	getValueType() interface{}
}

type SearchAttributesRO interface {
}

type SearchAttributesRW interface {
}

type QueryAttributesRO interface {
}

type QueryAttributesRW interface {
}

type AttributeLoadingType string

const (
	// this will load all attributes without locking
	LOAD_ALL_WITHOUT_LOCKING AttributeLoadingType = "LOAD_ALL_WITHOUT_LOCKING"
	// this will load all attributes but lock them exclusively for one request(execute/decide) at a time
	// this is to prevent racing condition of different states overwriting the attributes
	// Note that the lock will be released after execute/decide is completed, so it will allow commands to
	// execute in parallel.
	LOAD_ALL_WITH_EXCLUSIVE_LOCK AttributeLoadingType = "LOAD_ALL_WITH_EXCLUSIVE_LOCK"
	// same as LOAD_ALL_WITHOUT_LOCKING but only load part of the attributes(need to specified in policy)
	LOAD_PARTIAL_WITHOUT_LOCKING AttributeLoadingType = "LOAD_PARTIAL_WITHOUT_LOCKING"
	// same as LOAD_ALL_WITH_EXCLUSIVE_LOCK but only load part of the attributes(need to specified in policy)
	LOAD_PARTIAL_WITH_EXCLUSIVE_LOCK AttributeLoadingType = "LOAD_PARTIAL_WITH_EXCLUSIVE_LOCK"
)

type AttributeLoadingPolicy interface {
	GetAttributeLoadingType() AttributeLoadingType
	GetAttributeKeys() []string
}
