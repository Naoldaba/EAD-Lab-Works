package com.itsc.exam.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.itsc.enterprise.controller.TaskRegistrationController.*(..))")
    public void taskRegistrationMethods() {}

    @Before("taskRegistrationMethods()")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("Starting method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "taskRegistrationMethods()", returning = "result")
    public void afterMethod(JoinPoint joinPoint, Object result) {
        System.out.println("Completed method: " + joinPoint.getSignature().getName());
    }
}
