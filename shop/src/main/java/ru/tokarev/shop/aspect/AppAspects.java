package ru.tokarev.shop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Aspect
@Configuration
public class AppAspects implements Serializable {

    private static final long serialVersionUID = 5058967780626163061L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // https://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/aop.html
    @Before("execution(* ru.tokarev.shop.controller.*.*(..))")
    public void before(JoinPoint joinPoint){
        logger.info("Call of {}", joinPoint);
    }

    @Around("@annotation(ru.tokarev.shop.aspect.TrackTime)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Time Taken by {} is {}", joinPoint, timeTaken);

        return result;
    }
}
