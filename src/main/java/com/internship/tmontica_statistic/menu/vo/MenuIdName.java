package com.internship.tmontica_statistic.menu.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MenuIdName {
    private int id;
    private String nameKo;

    public boolean isThisId(int id){

        return this.id == id;
    }
}
