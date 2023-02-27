package com.example.springsecurity.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingService {

    @Pointcut("execution(* com.example.springsecurity.service.impl.UserServiceImpl.*(..))")
    public void callAtMyServicePublic() {
    }

    @Before("callAtMyServicePublic()")
    public void logBefore(JoinPoint jp) {
        log.info("Call method: {}.{}", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    @Before("@annotation(com.example.springsecurity.annotation.AspectLog) && args(id,..)")
    public void checkId(JoinPoint jp, Long id) {
        if (id <= 0) {
            log.error("Method: {}.{} not valid argument, message: " +
                            "Ну короче, id не может быть меньше нуля, и кстати равняться нулю тоже",
                    jp.getSignature().getDeclaringType().getName(), jp.getSignature().getName());
            throw new IllegalArgumentException("Ну короче, id не может быть меньше нуля, и кстати равняться нулю тоже");
        }
    }

    @After("execution(void com.example.springsecurity.service.impl.UserServiceImpl.*(..))")
    public void logAfterVoidMethod(JoinPoint jp) {
        log.info("Method: {}.{} done", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    @AfterReturning("@annotation(com.example.springsecurity.annotation.AspectLog)")
    public void logAfter(JoinPoint jp) {
        log.info("Method: {}.{} done", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    @AfterThrowing(pointcut = "callAtMyServicePublic()", throwing = "result")
    public void logAfterThrowing(JoinPoint jp,  RuntimeException result) {
        log.error("Method: {}.{} throw exception {}, message: {}",
                jp.getSignature().getDeclaringType().getName(), jp.getSignature().getName(),
                result.getClass(), result.getMessage());
    }
}


