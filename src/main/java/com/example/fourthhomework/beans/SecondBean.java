package com.example.fourthhomework.beans;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnBean(FirstBean.class)
public class SecondBean {
    public SecondBean() {
        System.out.println("Second bean, first bean exists!");
    }
}
