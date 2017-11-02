package com.ba.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("execution(* com.ba.controller.*.*(..))")
    protected void loggingOperation() {}
  
    @Before("loggingOperation()")
    @Order(1)
    public void logJoinPoint(JoinPoint joinPoint)
    {
    	logger.info("Entering Method :"+joinPoint.getSignature().getDeclaringTypeName()+
    			"." + joinPoint.getSignature().getName());
    }
      
    @AfterReturning(pointcut="loggingOperation()", returning = "result")
    @Order(2)
    public void logAfter(JoinPoint joinPoint, Object result)
    {
    	logger.info("Exiting from Method :"+joinPoint.getSignature().getName());
    }
  
  
}
