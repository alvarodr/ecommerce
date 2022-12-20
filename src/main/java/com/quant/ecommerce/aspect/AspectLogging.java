package com.quant.ecommerce.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public Publisher afterLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        Publisher result = (Publisher) joinPoint.proceed();

        if (result instanceof Mono) {
          return ((Mono) result).doOnSuccess(data -> logger.info("FINISH {} {}", joinPoint.getSignature().getName(), data));
        } else {
          return ((Flux) result).doOnSubscribe(data -> logger.info("FINISH {} {}", joinPoint.getSignature().getName(), data));
        }

    }

}
