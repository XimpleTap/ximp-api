package com.ximp.api.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggerAspect {
	
		Logger aspectLogger=Logger.getLogger(LoggerAspect.class);
		
		
		@Before("execution(* com.loyaltycard.api.products.controllers.*.*(..))")
		public void logApiCalls(JoinPoint joinPoint){
			aspectLogger.info(joinPoint.getSignature().getName()+" called");
		}

}
