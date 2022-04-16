package com.example.fourthhomework.beans;

import com.example.fourthhomework.conditions.NotWithDefaultCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Service
@Conditional(NotWithDefaultCondition.class)
public class ThirdBean {
    public ThirdBean() {
        System.out.println("Third Bean: 'Env variable set to non-default value!'");
    }
}
