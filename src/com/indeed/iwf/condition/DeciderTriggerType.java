package com.indeed.iwf.condition;

// this trigger type will decide when to invoke decide method based on conditions' statues
public enum DeciderTriggerType {
    ALL_CONDITION_COMPLETED, // this will wait for all conditions are completed. It will fail the workflow if any condition fails(e.g. activity failure)
    ANY_CONDITION_COMPLETED, // this will wait for any condition to be completed. It will fail the workflow if any condition fails(e.g. activity failure)
    ANY_CONDITION_CLOSED // this will wait for any condition to be closed. It won't fail the workflow if any condition fails(e.g. activity failure)
}
