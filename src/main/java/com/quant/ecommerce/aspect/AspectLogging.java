package com.quant.ecommerce.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Aspect
@Component
public class AspectLogging {

    @Before("execution(public * com.quant.ecommerce.service..*Service.*(..))")
    public void loggingExecution(JoinPoint joinPoint) {
        String arguments = Stream.of(joinPoint.getArgs()).map(Object::toString).collect(Collectors.joining(", "));
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        Object params[] = {joinPoint.getSignature().getName(), arguments};
        logger.info("START {} {}", params);
    }

    @Around("execution(public * com.quant.ecommerce.service..*Service.*(..)))")
    public Mono afterLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        Mono result = (Mono) joinPoint.proceed();
        return result.doOnSuccess(data -> logger.info("FINISH {} {}", joinPoint.getSignature().getName(), data));
    }

}
