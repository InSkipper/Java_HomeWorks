package com.example.fourthhomework.conditions;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.NoneNestedConditions;

public class NotWithDefaultCondition extends NoneNestedConditions {
    public NotWithDefaultCondition() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @ConditionalOnProperty(value = "my.env-variable", havingValue = "default")
    static class Condition {
    }
}
