package com.example.fourthhomework.beans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"test"})
public class FirstBean {
    public FirstBean() {
        System.out.println("First bean, profile set to test!");
    }
}
