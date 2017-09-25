package com.dav.spring.hibernate.common.logger;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class MyLogger.
 */
@Aspect
@Component
public class MyLogger {

    /**  Handle to the log file. */
    private final Log logger = LogFactory.getLog(getClass());

    /**
     * Instantiates a new my logger.
     */
    public MyLogger () {}

    /**
     * Log method access after.
     *
     * @param joinPoint the join point
     */
    @AfterReturning("execution(* com.dav.spring.hibernate.controller..*.*(..))")
    public void logMethodAccessAfter(JoinPoint joinPoint) {
    	logger.info("*************** Completed: " + joinPoint.getSignature().getName() + " ***************");
    }

    /**
     * Log method access before.
     *
     * @param joinPoint the join point
     */
    @Before("execution(* com.dav.spring.hibernate.controller..*.*(..))")
    public void logMethodAccessBefore(JoinPoint joinPoint) {
    	logger.info("*************** Starting: " + joinPoint.getSignature().getName() + " ***************");
    }

    /**
     * Business methods.
     */
    @Pointcut("execution( * com.dav.spring.hibernate.service..*.*(..))")
    public void businessMethods() { }

    /**
     * Call duration advice.
     *
     * @param pjp the pjp
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("businessMethods()")
    public Object callDurationAdvice(ProceedingJoinPoint pjp) throws Throwable {
    	MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        long start = System.currentTimeMillis();
		logger.info("***** Going to call the method. " + method.getName() + " *****");
		Object output = pjp.proceed();
		logger.info("***** Method execution completed. *****");
		long elapsedTime = System.currentTimeMillis() - start;
		logger.info("***** Method execution time: " + elapsedTime + " milliseconds. *****");
		logger.info("***** End method. " + method.getName() + " *****");

        return output;
    }
}