package com.example.defaultproject.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class WithApiMaxCountAspect {
    @Value("${api.max-count}")
    private int maxCount;
    private int currentCount;

    @Pointcut("@annotation(com.example.defaultproject.annotation.WithApiMaxCount)")
    public void withApiMaxCountMethods() {
    }

    @Before("withApiMaxCountMethods()")
    public void checkMaxCount(JoinPoint point) {
        var methodName = String.format("%s.%s",
                point.getStaticPart().getSignature().getDeclaringType().getName(),
                point.getSignature().getName());
        if (maxCount > currentCount) {
            currentCount++;
            log.info("Method {} was called {} times out of {}", methodName, currentCount, maxCount);
        } else {
            log.warn("Method {} has reached the max number of calls : {}", methodName, maxCount);
            throw new RuntimeException("MAX number of calls");
        }
    }
}
