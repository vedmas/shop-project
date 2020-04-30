package ru.tokarev.shop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Aspect
@Configuration
@Slf4j
public class AppAspects implements Serializable {

    private static final long serialVersionUID = 5058967780626163061L;

    // https://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/aop.html
    @Before("execution(* ru.tokarev.shop.controller.*.*(..))")
    public void before(JoinPoint joinPoint){
        log.info("Call of {}", joinPoint);
    }

    @Around("@annotation(ru.tokarev.shop.aspect.TrackTime)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Time Taken by {} is {}", joinPoint, timeTaken);

        return result;
    }
}
