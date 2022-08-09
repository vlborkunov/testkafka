package com.vlborkunov.testkafka.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TestAOP {

    @AfterReturning(pointcut = "execution(* com.vlborkunov.testkafka.service.ProductService.get(..))",
            returning = "retValue")
    public void logGet(JoinPoint joinPoint,Object retValue) {
        log.info("Точка: {}", joinPoint.getThis());
        log.info("Static part: {}", joinPoint.getStaticPart());
        log.info("Получаем объект: {}", retValue);
    }

}
