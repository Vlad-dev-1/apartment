package com.example.function_module.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class RentEventListener {

    public static final String POINT_CUT = "execution(* com.example.function_module.service.impl.ApartmentServiceImpl.getApartmentInfoById(..))";

    @AfterReturning(pointcut = POINT_CUT, returning = "result")
    public void saveShowStatistic(JoinPoint joinPoint, Object result){


        log.info("Начало работы слушателя событий");
        String name = joinPoint.getSignature().getName();
        String resultString = result.toString();
        log.info("Слушатель закончил работу");
    }
}
