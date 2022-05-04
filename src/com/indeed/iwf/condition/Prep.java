package com.indeed.iwf.condition;

import java.util.Arrays;
import java.util.List;

public class Prep {
    private final List<BaseCondition> conditions;
    private final DeciderTriggerType deciderTriggerType;

    private Prep(final List<BaseCondition> conditions, final DeciderTriggerType deciderTriggerType) {
        this.conditions = conditions;
        this.deciderTriggerType = deciderTriggerType;
    }

    public static Prep prepareAllConditionCompleted(final BaseCondition... conditions) {
        return new Prep(Arrays.asList(conditions), DeciderTriggerType.ALL_CONDITION_COMPLETED);
    }

    public static Prep prepareAnyConditionCompleted(final BaseCondition... conditions) {
        return new Prep(Arrays.asList(conditions), DeciderTriggerType.ANY_CONDITION_COMPLETED);
    }

    public static Prep prepareAnyConditionClosed(final BaseCondition... conditions) {
        return new Prep(Arrays.asList(conditions), DeciderTriggerType.ANY_CONDITION_CLOSED);
    }
}
