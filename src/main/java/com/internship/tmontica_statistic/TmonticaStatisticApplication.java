package com.internship.tmontica_statistic;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class TmonticaStatisticApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmonticaStatisticApplication.class, args);
    }
//
}
