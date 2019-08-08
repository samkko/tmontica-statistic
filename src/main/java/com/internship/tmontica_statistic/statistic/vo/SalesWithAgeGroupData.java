package com.internship.tmontica_statistic.statistic.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SalesWithAgeGroupData {

    private String ageGroup;
    private int totalPrice;

    public SalesWithAgeGroupData(String ageGroup, int totalPrice){
        this.ageGroup = ageGroup;
        this.totalPrice = totalPrice;
    }
}
