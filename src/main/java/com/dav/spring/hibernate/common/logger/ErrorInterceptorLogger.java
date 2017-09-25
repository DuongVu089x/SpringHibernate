package com.dav.spring.hibernate.common.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorInterceptorLogger.
 */
@Aspect
@Component
public class ErrorInterceptorLogger {

	/**  Handle to the log file. */
    private final Log logger = LogFactory.getLog(getClass());

    /**
     * Error interceptor.
     *
     * @param joinPoint the join point
     * @param throwEx the throw ex
     */
    @AfterThrowing(pointcut = "execution(* com.dav.spring.hibernate.controller..*.*(..))", throwing = "throwEx")
    public void errorInterceptor(JoinPoint joinPoint, Throwable throwEx) {
        logger.info("*************** Start-Error: " + joinPoint.getSignature().getName() + " ***************");
        StringBuffer strEx = getContentException(throwEx);
        logger.error(strEx);
        logger.info("*************** End-Error: " + joinPoint.getSignature().getName() + " ***************");

    }

	/**
	 * Error token parse.
	 *
	 * @param joinPoint the join point
	 * @param throwEx the throw ex
	 */
	@AfterThrowing(pointcut = "execution(* com.dav.spring.hibernate.config..*.*(..))", throwing = "throwEx")
	public void errorTokenParse(JoinPoint joinPoint, Throwable throwEx) {
		logger.info("*************** Start Parse Token Error ***************");
		StringBuffer strEx = getContentException(throwEx);
		logger.error(strEx);
		logger.info("*************** End Parse Token Error ***************");
	}

    /**
     * Gets the content exception.
     *
     * @param throwEx the throw ex
     * @return the content exception
     */
    private static StringBuffer getContentException(Throwable throwEx) {
        StringBuffer strEx = new StringBuffer();
        strEx.append(throwEx);
        strEx.append(System.lineSeparator());

        for (int i = 0; i < throwEx.getStackTrace().length; i++) {
            strEx.append(throwEx.getStackTrace()[i]);
            strEx.append(System.lineSeparator());
        }
        return strEx;
    }
}
