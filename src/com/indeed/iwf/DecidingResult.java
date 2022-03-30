package com.indeed.iwf;

import java.util.List;
import java.util.Map;

public class DecidingResult<O> {

    /**
     * when using this constructor, it means the completed conditions are enough so state is completing
     *
     * @param output                 the output of the state
     * @param nextStateIDs           the next states that the workflow will be moved to
     * @param upsertSearchAttributes the search attributes that will be upsert
     * @param upsertMemo             the memo that will be upsert
     */
    public DecidingResult(final O output, final List<String> nextStateIDs, final Map<String, Object> upsertSearchAttributes, final Map<String, Object> upsertMemo) {
        this.output = output;
        this.nextStateIDs = nextStateIDs;
        this.upsertSearchAttributes = upsertSearchAttributes;
        this.upsertMemo = upsertMemo;
        waitForMoreConditions = false;
    }

    /**
     * when using this method, it means conditions are not enough so workflow will wait for more conditions to complete
     *
     * @return
     */
    public static DecidingResult waitForMoreConditions() {
        return new DecidingResult(true);
    }

    private DecidingResult(boolean waitForMoreConditions) {
        this.waitForMoreConditions = true;
        output = null;
        nextStateIDs = null;
        upsertMemo = null;
        upsertSearchAttributes = null;
    }

    private final boolean waitForMoreConditions;
    private final O output;
    private final List<String> nextStateIDs;
    private final Map<String, Object> upsertSearchAttributes;
    private final Map<String, Object> upsertMemo;

    public O getOutput() {
        return output;
    }

    public List<String> getNextStateIDs() {
        return nextStateIDs;
    }

    public Map<String, Object> getUpsertSearchAttributes() {
        return upsertSearchAttributes;
    }

    public Map<String, Object> getUpsertMemo() {
        return upsertMemo;
    }
}
