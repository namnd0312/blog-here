package com.namnd.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    @Pointcut("execution(* com.namnd.service..*(..)) || " +
            "execution(* com.namnd.dao..*(..))")
    public void applicationLayer() {}


    @Around("applicationLayer()")
    public Object logExecutionDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
//        String traceId = MDC.get("traceId");
        Object[] args = joinPoint.getArgs();

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;

            log.info("[{}] Method: {} | Args: {} | Result: {} | Time: {} ms",
                    MDC.get("traceId"),
                    methodName,
                    Arrays.toString(args),
                    result != null ? result.getClass().getSimpleName() : "null",
                    duration
            );

            return result;
        } catch (Throwable ex) {
            long duration = System.currentTimeMillis() - start;
            log.error("[{}] Method: {} | Args: {} | ERROR: {} | Time: {} ms",
                    MDC.get("traceId"),
                    methodName,
                    Arrays.toString(args),
                    ex.getMessage(),
                    duration
            );
            throw ex;
        }
    }
}
