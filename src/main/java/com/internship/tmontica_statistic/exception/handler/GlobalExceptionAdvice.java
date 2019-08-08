package com.internship.tmontica_statistic.exception.handler;

import com.internship.tmontica_statistic.exception.TmonTicaExceptionFormat;
import com.internship.tmontica_statistic.statistic.exception.StatisticException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    // 통계
    @ExceptionHandler(StatisticException.class)
    public ResponseEntity<TmonTicaExceptionFormat> handleStatisticException(StatisticException  e){
        log.debug("StatisticExceptionMessage : {}", e.getMessage());
        return new ResponseEntity<>(new TmonTicaExceptionFormat(e.getLocalizedMessage(), e.getMessage()), e.getStatisticExceptionType().getResponseType());
    }
}
